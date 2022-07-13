package com.example.estest.config;

import java.io.IOException;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.fastjson.JSONObject;

/**
 * @author 15711
 */
@Configuration
public class EsConfig {
	public static final RequestOptions COMMON_OPTIONS;
	static {
    RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
    COMMON_OPTIONS = builder.build();
	}
	/**
	*方式一：无账号密码连接方式
	**/
	@Bean
	public RestHighLevelClient esRestClient(){
		RestHighLevelClient client = new RestHighLevelClient(
			RestClient.builder(
                new HttpHost("42.192.83.41", 9200, "http")
				));
			return client;
}

	public static RestHighLevelClient getRestHighLevelClient(){
		RestHighLevelClient client = new RestHighLevelClient(
			RestClient.builder(
				new HttpHost("127.0.0.1", 9200, "http")
			));
		return client;
	}

	public static void main(String[] args) throws IOException {
		RestHighLevelClient restHighLevelClient = getRestHighLevelClient();
		RestClient restClient = restHighLevelClient.getLowLevelClient();
		Request request = new Request("GET","/_cat/indices?v&pretty");
		Response response = restClient.performRequest(request);
		System.out.println(JSONObject.toJSON(response));
		String s = EntityUtils.toString(response.getEntity());
		System.out.println(s);
	}
}