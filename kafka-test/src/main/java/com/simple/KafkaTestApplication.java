package com.simple;

import java.lang.reflect.Field;
import java.util.Map;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KafkaTestApplication {

	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
		ConfigurableApplicationContext run = SpringApplication.run(KafkaTestApplication.class, args);
		ConfigurableListableBeanFactory beanFactory = run.getBeanFactory();
		Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
		singletonObjects.setAccessible(true);
		Map<String, Object> map = (Map<String, Object>) singletonObjects.get(beanFactory);
		map.forEach((k,v)->{
			System.out.println(k);
			System.out.println(v);
		});
	}

}
