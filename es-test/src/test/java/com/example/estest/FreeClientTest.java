package com.example.estest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.estest.config.ElasticsearchConfig;
import com.example.estest.service.BankService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=EsTestApplication.class)
@Slf4j
public class FreeClientTest {

    private String index;

    private String type;

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private BankService bankService;

    @Before
    public void prepare() {
        System.out.println(111);
        index = "demo";
        type = "demo";
    }


    @Test
    public void addTest1() {
        IndexRequest request = new IndexRequest("es_user");
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user_name", "张启桥");
        jsonMap.put("post_date", new Date());
        jsonMap.put("age", 23);
        jsonMap.put("gender", "男");
        jsonMap.put("height", 180);
        jsonMap.put("address", "四川省成都市犀浦镇百草路12号");
        request.source(jsonMap);
        IndexResponse response = null;
        try {
            response = restHighLevelClient.index(request, ElasticsearchConfig.COMMON_OPTIONS);
            if(response.getResult().name().equalsIgnoreCase("created")){
                System.out.println("创建成功！");
            }else {
                System.out.println("失败！");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void queryTest() {
//        testService.complexQuery()
//    }




}


//    @Test
//    public void addTest() {
//        IndexRequest indexRequest = new IndexRequest(index, type);
//        News news = new News();
//        news.setTitle("中国产小型无人机的“对手”来了，俄微型拦截导弹便宜量又多");
//        news.setTag("军事");
//        news.setPublishTime("2018-01-24T23:59:30Z");
//        String source = JSONObject.toJSONString(news);
//        indexRequest.source(source, XContentType.JSON);
//        try {
//            restHighLevelClient.index(indexRequest);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }



//    @Test
//    public void queryTest(){
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//        sourceBuilder.from(0);
//        sourceBuilder.size(10);
//        sourceBuilder.fetchSource(new String[]{"title"}, new String[]{});
//        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "费德勒");
//        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("tag", "体育");
//        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("publishTime");
//        rangeQueryBuilder.gte("2018-01-26T08:00:00Z");
//        rangeQueryBuilder.lte("2018-01-26T20:00:00Z");
//        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
//        boolBuilder.must(matchQueryBuilder);
//        boolBuilder.must(termQueryBuilder);
//        boolBuilder.must(rangeQueryBuilder);
//        sourceBuilder.query(boolBuilder);
//        SearchRequest searchRequest = new SearchRequest(index);
//        searchRequest.types(type);
//        searchRequest.source(sourceBuilder);
//        try {
//            SearchResponse response = rhlClient.search(searchRequest);
//            System.out.println(response);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

