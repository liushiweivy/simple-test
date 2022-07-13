package com.example.estest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.alibaba.fastjson.JSON;
import com.example.estest.vo.PqsLogVo;

@SpringBootTest
class EsTestApplicationTests {

	@Test
	void contextLoads() {
	}

	@Resource
	private RestHighLevelClient restHighLevelClient;

	@Test
	public void search(){
		SearchRequest searchRequest = new SearchRequest("pqslog");

		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

		//默认分词查询
		QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("url", "kf")
			.fuzziness(Fuzziness.AUTO) //模糊查询
			.prefixLength(3) // 在匹配查询上设置前缀长度选项,指明区分词项的共同前缀长度，默认是0
			.maxExpansions(10); //设置最大扩展选项以控制查询的模糊过程

		//查询条件 添加，user = kimchy
		//sourceBuilder.query(QueryBuilders.termQuery("user", "kimchy"));
		sourceBuilder.query(matchQueryBuilder);


		//查询开始-结束 。可以用来分页使用
		sourceBuilder.from(0);
		sourceBuilder.size(5);

		//设置一个可选的超时，控制允许搜索的时间。
		sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

		//排序
//		sourceBuilder.sort(new FieldSortBuilder("id").order(SortOrder.ASC));

		searchRequest.source(sourceBuilder);

		try {
			SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
			//处理搜索结果
			RestStatus restStatus = searchResponse.status();
			if (restStatus != RestStatus.OK){
				System.out.println("搜索错误");
			}
			List<PqsLogVo> list = new ArrayList<>();
			SearchHits hits = searchResponse.getHits();
			hits.forEach(item -> list.add(JSON.parseObject(item.getSourceAsString(), PqsLogVo.class)));
			System.out.println(list);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
