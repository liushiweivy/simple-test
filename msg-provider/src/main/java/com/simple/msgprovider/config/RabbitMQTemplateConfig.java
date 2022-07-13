package com.simple.msgprovider.config;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Listener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.Queue.Bind;

/**
 * @author jaymin
 * 2020/12/30 17:10
 */
@Configuration
@Slf4j
public class RabbitMQTemplateConfig {

    /**
     * 队列名
     */
    public static final String SIMPLE_QUEUE_NAME = "direct";
    /**
     * 处理对象的MQ队列
     */
    public static final String HANDLER_OBJECT_QUEUE_NAME = "com.xjm.mq.simple.object";

    /**
     * 交换机
     */
    public static final String EXCHANGE_TEST = "topic.exchange";

    /**
     * 队列名
     */
    public static final String FOOTBALL_QUEUE_NAME = "football";
    public static final String SPORT_TOPIC_NAME = "topic.sport.#";

    public static final String BOOK_QUEUE_NAME = "book";
    public static final String BOOK_TOPIC_NAME = "topic.book.#";

    public static final String BASKETBALL_QUEUE_NAME = "basketball";

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }


    @Bean
    public MessageConverter jackson2JsonMessageConverter(){
        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
        return jackson2JsonMessageConverter;
    }

    @Bean
    public Queue queue1() {
        Map<String, Object> map = new HashMap<>();
        // 队列中的消息未被消费则10秒后过期
        map.put("x-message-ttl", 100000);
        map.put("x-dead-letter-exchange", "deadExchange");
        map.put("x-max-length", 10);
        map.put("x-dead-letter-routing-key", "dlx.hh");
        return new Queue("ttl", true, false, false, map);
    }

    @Bean
    public Queue deadQueue() {
        // 队列中的消息未被消费则10秒后过期
        return new Queue("deadQueue", true, false, false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("ttl");
    }

    @Bean
    public TopicExchange deadExchange() {
        return new TopicExchange("deadExchange");
    }

    @Bean
    public Binding bind() {
        return BindingBuilder.bind(queue1()).to(exchange()).with("ttl.#");
    }

    @Bean
    public Binding bindDead() {
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with("dlx.#");
    }
}