package com.example.estest.service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.estest.config.ElasticsearchConfig;
import com.example.estest.entity.Bank;
import com.example.estest.entity.EsQueryParams;

/**
 * @author simple
 * @date 2021/6/19 7:53
 */
@Service
@Slf4j
public class BankService {

	@Resource
	private RestHighLevelClient restHighLevelClient;

	public boolean addBank(Bank bank) {
		IndexRequest request = new IndexRequest("bank");
		Map<String, Object> jsonMap = JSON.parseObject(JSON.toJSONString(bank));
		request.source(jsonMap);
		IndexResponse response = null;
		try {
			response = restHighLevelClient.index(request, ElasticsearchConfig.COMMON_OPTIONS);
			if (response.getResult().name().equalsIgnoreCase("created")) {
				System.out.println("创建成功！");
				return true;
			} else {
				System.out.println("失败！");
				return false;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean addObject(String index,Map<String, String> jsonMap) {
		IndexRequest request = new IndexRequest(index);
		request.source(jsonMap);
		IndexResponse response = null;
		try {
			response = restHighLevelClient.index(request, ElasticsearchConfig.COMMON_OPTIONS);
			if (response.getResult().name().equalsIgnoreCase("created")) {
				System.out.println("创建成功！");
				return true;
			} else {
				System.out.println("失败！");
				return false;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean updateBank(Bank bank) {
		Map<String, Object> jsonMap = JSON.parseObject(JSON.toJSONString(bank));
		UpdateRequest updateRequest = new UpdateRequest("bank",bank.getAccountNumber().toString()).doc(jsonMap);
		UpdateResponse response = null;
		try {
			response = restHighLevelClient.update(updateRequest, ElasticsearchConfig.COMMON_OPTIONS);
			if (response.status().getStatus()==200) {
				System.out.println("更新成功！");
				return true;
			} else {
				System.out.println("失败！");
				return false;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Map<String, Object> queryList(EsQueryParams esQueryParams) {
		Integer start = esQueryParams.getStart() == null ? 0 : esQueryParams.getStart() - 1;
		Integer limit = esQueryParams.getLimit() == null ? 0 : esQueryParams.getLimit();
		String keyWord = esQueryParams.getKeyWord();
		Integer minBalance = esQueryParams.getMinBalance();
		Integer maxBalance = esQueryParams.getMaxBalance();
		String address = esQueryParams.getAddress();
		String city = esQueryParams.getCity();
		String firstname = esQueryParams.getFirstname();
		String employer = esQueryParams.getEmployer();
		String userId = esQueryParams.getUserId();
		//1.创建请求
		SearchRequest request = new SearchRequest();
		//这里是7.4.2不需要指定type了，8以后就没有type了
		request.indices("bank");
		//2、创建请求参数
		SearchSourceBuilder ssb = new SearchSourceBuilder();
		//分页并排序(第一页是从0开始的，所以上面的start-1)
		ssb
			.from(start)
			.size(limit)
			.sort("balance", SortOrder.DESC) //排序
			.trackTotalHits(true);//查全部数据(如果不写或者写false当总记录数超过10000时会返回总数10000,配置为true就会返回真实条数)
		//指定返回字段
		ssb.fetchSource(new String[]{"id","account_number", "balance", "firstname", "lastname", "age", "gender",
			"address", "employer", "email", "city", "state"}, new String[]{});
		BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
		/**
		 * //QueryBuilders.matchQuery()表示模糊查询----用来做keyWord的搜索
		 * //QueryBuilders.termQuery()表示精确查询
		 /**
		 * 精确匹配:要采用 字段.keyword 才匹配得到，直接匹配那么匹配不到。原因:
		 * elasticsearch 里默认的IK分词器是会将每一个中文都进行了分词的切割，所以你直接想查一整个词，或者一整句话是无返回结果的
		 *
		 */
		//精确匹配
		if (!StringUtils.isBlank(address)) {
			boolQueryBuilder.filter(QueryBuilders.termQuery("address.keyword", address));
		}
		if (!StringUtils.isBlank(city)) {
			boolQueryBuilder.filter(QueryBuilders.termQuery("city.keyword", city));
		}
		if (!StringUtils.isBlank(employer)) {
			boolQueryBuilder.filter(QueryBuilders.termQuery("employer.keyword", employer));
		}
		/**
		 *精确匹配(可能多字段,例如userId传一个或多个，用逗号隔开：1,2,3,4,5,6)
		 **/
		if (!StringUtils.isBlank(userId)) {
			String[] userIdArr = userId.split(",");
			String userIdsStr = "";
			if (userIdArr != null && userIdArr.length >= 2) {
				for (String oneUserId : userIdArr) {
					userIdsStr += !StringUtils.isBlank(userIdsStr) ? (" " + oneUserId) : oneUserId;
				}
				/**
				 *例如：address匹配 "唐人" 或者 "2号"
				 boolQueryBuilder.must(QueryBuilders.matchQuery("address", "唐人 2号").operator(Operator.OR));
				 如果用Operator.AND就是代表"唐人" 并且 "2号"
				 注意:这里没有.keyword
				 **/
				boolQueryBuilder.filter(QueryBuilders.matchQuery("userId", userIdsStr).operator(Operator.OR));
			} else if (userIdArr != null && userIdArr.length <= 1) {
				userIdsStr = userIdArr[0];
				boolQueryBuilder.filter(QueryBuilders.termsQuery("userId.keyword", userIdsStr));
			}
		}
		//模糊匹配
		if (!StringUtils.isBlank(firstname)) {
			boolQueryBuilder.filter(QueryBuilders.matchQuery("firstname", firstname));
		}
		//多字段模糊匹配要用matchPhraseQuery，否则它会分词，例如：搜索“学校”，会被分为 "学","校"，会查出包含这两个之一的数据，但是我们只想要包含"学校"整体的数据。所以必须用短语查询matchPhraseQuery
		if (!StringUtils.isBlank(keyWord)) {
			boolQueryBuilder.
				should(QueryBuilders.matchPhraseQuery("lastname", keyWord)).
				should(QueryBuilders.matchPhraseQuery("email", keyWord)).
				should(QueryBuilders.matchPhraseQuery("employer", keyWord));
		}

		if (minBalance != null && maxBalance != null) {
			//范围查找(只针对数值，不能针对字符串)
			boolQueryBuilder.filter(QueryBuilders.rangeQuery("balance").gte(minBalance).lte(maxBalance));
		}
		ssb.query(boolQueryBuilder);
		log.info("获取到的请求参数:{}", ssb);
		request.source(ssb);
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		SearchResponse response = null;
		RestStatus status = null;
		try {
			response = restHighLevelClient.search(request, ElasticsearchConfig.COMMON_OPTIONS);
			status = response.status();
			map.put("status", status);
			long totalHits = response.getHits().getTotalHits().value;
			Integer totalPage = (int) Math.ceil((double) totalHits / limit);
			map.put("currPage", start);
			map.put("pageSize ", limit);
			map.put("totalPage", totalPage);
			map.put("totalCount ", totalHits);
			SearchHit[] searchHits = response.getHits().getHits();
			for (SearchHit hit : searchHits) {
				Map<String, Object> sourceAsMap = hit.getSourceAsMap();
				String index = hit.getIndex();
				list.add(sourceAsMap);
			}
			map.put("list", list);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("查询失败");
			return null;
		}
		log.info(JSONObject.toJSONString(map));
		return map;
	}

}
