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
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.estest.config.ElasticsearchConfig;
import com.example.estest.dto.EsLogDto;
import com.example.estest.dto.PqsLogDto;
import com.example.estest.dto.PqsLogQuery;

/**
 * @author simple
 * @date 2021/6/19 7:53
 */
@Service
@Slf4j
public class EsLogService {

	@Resource
	private RestHighLevelClient restHighLevelClient;

	public String addPqsLog(PqsLogDto pqsLogDto) {
		IndexRequest request = new IndexRequest("pqslog");
		log.info(JSON.toJSONString(pqsLogDto));
		Map<String, Object> jsonMap = JSON.parseObject(JSON.toJSONString(pqsLogDto));
		request.source(jsonMap);
		IndexResponse response = null;
		try {
			response = restHighLevelClient.index(request, ElasticsearchConfig.COMMON_OPTIONS);
			if (response.getResult().name().equalsIgnoreCase("created")) {
				System.out.println("创建成功！");
				return JSON.toJSONString(pqsLogDto);
			} else {
				System.out.println("失败！");
				return JSON.toJSONString(pqsLogDto);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return JSON.toJSONString(pqsLogDto);
	}


	public Map<String, Object> getPqsLogList(PqsLogQuery query) {
		int start = query.getStart() == null ? 0 : query.getStart() - 1;
		int limit = query.getLimit() == null ? 10 : query.getLimit();
		SearchRequest request = new SearchRequest();
		//这里是7.4.2不需要指定type了，8以后就没有type了
		request.indices("pqslog");
		//2、创建请求参数
		SearchSourceBuilder ssb = new SearchSourceBuilder();
		ssb
			.from(start)
			.size(limit)
			.trackTotalHits(true);
		//指定返回字段
		ssb.fetchSource(new String[]{"id","requestObject", "requestObject.paraMap.chlId","responseObject"},
			new String[]{});
		BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
		//精确匹配
		String busiTyp = query.getBusiTyp();
		if (!StringUtils.isBlank(busiTyp)) {
			boolQueryBuilder.filter(QueryBuilders.termQuery("busiTyp.keyword", busiTyp));
		}
		String chlId = query.getChlId();
		if (!StringUtils.isBlank(chlId)) {
			boolQueryBuilder.filter(QueryBuilders.termQuery("chlId.keyword", chlId));
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

	public boolean addEsLog(EsLogDto apiLogDto) {
		IndexRequest request = new IndexRequest("log");
		Map<String, Object> jsonMap = JSON.parseObject(JSON.toJSONString(apiLogDto));
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
}


