package com.example.estest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 读取配置文件的自定义常量
 * @author pibigstar
 *
 */
@Configuration
@PropertySource("classpath:myconfig.properties")
@ConfigurationProperties(prefix="parsevip")
public class MyConfiguration {
	
	private String filePath;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
