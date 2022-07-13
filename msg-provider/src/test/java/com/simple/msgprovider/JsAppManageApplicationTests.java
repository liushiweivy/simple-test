package com.simple.msgprovider;
 
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
/**
 * 测试用例，controller层，可测试所有controller
 * @description
 * @author GZY
 * 2019年3月18日 上午11:07:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JsAppManageApplicationTests {
 
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
 
    /**
     * 获取最新app信息
     */
	@Test
	public void TestGetAppLatestVersion() throws Exception{
		RequestBuilder request = null;
		//构造请求
		request = post("/test");
		//执行请求
		mockMvc.perform(request) 
		        .andExpect(status().isOk())//返回HTTP状态为200
		        .andDo(print());//打印结果
		        //.andReturn();//想要返回结果，使用此方法
		
	}
	
	/**
	 * 用户通过微信方式扫描下载APP，则会提示使用浏览器打开地址
	 */
	@Test
	public void TestDownloadAppByMicroMessenger() throws Exception{
		RequestBuilder request = null;
		//构造请求
		request = get("/appProducer/downloadApp")
				.header("user-agent", "MicroMessenger")
				.param("appId", "1001"); 
		//执行请求
		mockMvc.perform(request) 
		        .andExpect(status().isOk())//返回HTTP状态为200
		        .andExpect(content().string(containsString("选择浏览器打开即可")))//返回结果中需包含的文字
		        .andDo(print());//打印结果
		
	}
	
	/**
	 * 用户通过Android扫描下载APP
	 */
	@Test
	public void TestDownloadAppByAndroid() throws Exception{
		RequestBuilder request = null;
		//构造请求
		request = get("/appProducer/downloadApp")
				.header("user-agent", "Android")
				.param("appId", "1001"); 
		//执行请求
		mockMvc.perform(request) 
		        .andExpect(status().isOk())//返回HTTP状态为200
		        .andDo(print());//打印结果
		
	}
	
	/**
	 * 用户通过iPhone扫描下载APP，则会重定向至评苹果APP官网
	 */
	@Test
	public void TestDownloadAppByIphone() throws Exception{
		RequestBuilder request = null;
		//构造请求
		request = get("/appProducer/downloadApp")
				.header("user-agent", "iPhone")
				.param("appId", "1001"); 
		//执行请求
		mockMvc.perform(request) 
				.andExpect(status().is3xxRedirection())//表示页面被重定向
				.andExpect(redirectedUrl("https://www.apple.com/cn/itunes/charts/"))//验证处理完请求后重定向的url
		        .andDo(print());//打印结果
		
	}
	
	/**
	 * 用户通过iPad扫描下载APP，则会重定向至评苹果APP官网
	 */
	@Test
	public void TestDownloadAppByIPad() throws Exception{
		RequestBuilder request = null;
		//构造请求
		request = get("/appProducer/downloadApp")
				.header("user-agent", "iPad")
				.param("appId", "1001"); 
		//执行请求
		mockMvc.perform(request) 
		        .andExpect(status().is3xxRedirection())//表示页面被重定向
		        .andExpect(redirectedUrl("https://www.apple.com/cn/itunes/charts/"))//验证处理完请求后重定向的url
		        .andDo(print());//打印结果
		
	}
	
	/**
	 * 用户通过其他方式扫描下载APP，则会提示仅支持的下载方式
	 */
	@Test
	public void TestDownloadAppByOther() throws Exception{
		RequestBuilder request = null;
		//构造请求
		request = get("/appProducer/downloadApp")
				.header("user-agent", "other")
				.param("appId", "1001"); 
		//执行请求
		mockMvc.perform(request) 
		        .andExpect(status().isOk())//返回HTTP状态为200
		        .andExpect(content().string(containsString("<h1>出现该页面可能是以下原因</h1>")))
		        .andDo(print());//打印结果
		
	}
	
	/**
	 * APP升级
	 */
	@Test
	public void TestUpgradeApp() throws Exception{
		RequestBuilder request = null;
		//构造请求
		request = get("/appProducer/upgradeApp")
				.param("appId", "1001"); 
		//执行请求
		mockMvc.perform(request) 
		        .andExpect(status().isOk())//返回HTTP状态为200
		        .andDo(print());//打印结果
		
	}
}