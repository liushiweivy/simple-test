package com.simple.kafka;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.simple.dto.IndicatorDto;
import com.simple.dto.LogDto;
import com.simple.dto.ZcApiLog;
import com.simple.dto.ZcApiLogDto;

/**
 * 类功能描述：<br>
 * <ul>
 * <li>类功能描述1<br>
 * <li>类功能描述2<br>
 * <li>类功能描述3<br>
 * </ul>
 * 修改记录：<br>
 * <ul>
 * <li>修改记录描述1<br>
 * <li>修改记录描述2<br>
 * <li>修改记录描述3<br>
 * </ul>
 * @author xuefl
 * @version 5.0 since 2020-01-13
 */
@Component
@Slf4j
public class KafkaConsumer {

	private static ConcurrentHashMap<String, ZcApiLogDto> map = new ConcurrentHashMap<>();
	private static ConcurrentHashMap<String, ReentrantLock> lockMap = new ConcurrentHashMap<>();


	@KafkaListener(topics = KafkaProducer.TOPIC_TEST, groupId = KafkaProducer.TOPIC_GROUP1)

	public void topic_test(ConsumerRecord<?, ?> record, Acknowledgment ack,
		@Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

		Optional message = Optional.ofNullable(record.value());
		if (message.isPresent()) {
			Object msg = message.get();
			log.info("topic_test 消费了： Topic:" + topic + ",Message:" + msg);
			ack.acknowledge();
		}
	}

	@KafkaListener(topics = KafkaProducer.TOPIC_TEST, groupId = KafkaProducer.TOPIC_GROUP2)
	public void topic_test1(ConsumerRecord<?, ?> record, Acknowledgment ack,
		@Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

		Optional message = Optional.ofNullable(record.value());
		if (message.isPresent()) {
			Object msg = message.get();
			log.info("topic_test1 消费了： Topic:" + topic + ",Message:" + msg);
			ack.acknowledge();
		}
	}

	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null) {
				emptyNames.add(pd.getName());
			}
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	@KafkaListener(topics = {"zcApiLog"}, groupId = KafkaProducer.TOPIC_GROUP1)
	public void zcApiLogListener(ConsumerRecord<String, String> record, Acknowledgment ack,
		@Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
		String value = record.value();
		ZcApiLogDto logDto = JSONObject.parseObject(value, ZcApiLogDto.class);
		Map<String, IndicatorDto> newIndicatorMap = logDto.getIndicatorMap();
		log.info("zcApiLogListener 消费了,Topic:{},Message:{}", topic, JSONObject.toJSONString(logDto));
		String uri = logDto.getUri();
		log.info("消费前map:{}", JSONObject.toJSON(map));
		if (map.containsKey(uri)) {
			ZcApiLogDto apiLogDto = map.get(uri);
			Map<String, IndicatorDto> indicatorMap = apiLogDto.getIndicatorMap();
			BeanUtils.copyProperties(logDto, apiLogDto, getNullPropertyNames(logDto));
			Map<String, IndicatorDto> newMap = mergeMap(indicatorMap, newIndicatorMap);
			apiLogDto.setIndicatorMap(newMap);
		} else {
			map.put(uri, logDto);
		}
		log.info("消费后map:{}", JSONObject.toJSON(map));
	}

	private Map<String, IndicatorDto> mergeMap(Map<String, IndicatorDto> indicatorMap,
		Map<String, IndicatorDto> newIndicatorMap) {
		if (null == indicatorMap) {
			return newIndicatorMap;
		}
		if (null == newIndicatorMap) {
			return indicatorMap;
		}

		newIndicatorMap.forEach((k,v)-> {
			if (indicatorMap.containsKey(k)) {
				IndicatorDto indicatorDto = indicatorMap.get(k);
				indicatorDto.addCount(v);
			} else {
				indicatorMap.put(k, v);
			}
		});
		return newIndicatorMap;
	}

	@Scheduled(fixedDelay = 15000)
	public synchronized void task() throws InterruptedException {

		log.info("开始处理收集到的日志");
		Thread.sleep(10000);
		log.info("map{}", JSONObject.toJSONString(map));
		map.forEach((k,v)->{
			ZcApiLog zcApiLog = new ZcApiLog();

		});
		map.clear();
		log.info("处理收集到的日志结束");
	}
}

//	@Scheduled(fixedDelay = 15000)
//	public synchronized void task() {
//		log.info("map{}", JSONObject.toJSONString(map));
//		map.clear();
//	}

