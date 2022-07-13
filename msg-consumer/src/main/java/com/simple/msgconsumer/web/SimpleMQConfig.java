package com.simple.msgconsumer.web;


import lombok.Getter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;

/**
 * @author jaymin<br>
 * 简单的MQ配置类<br>
 * 2020/12/30 17:41
 */
@Configuration
@Getter
public class SimpleMQConfig {
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
    public Queue simpleQueue() {
        return new Queue(SIMPLE_QUEUE_NAME);
    }

    @Bean
    public Queue handleFootQueue() {
        return new Queue(FOOTBALL_QUEUE_NAME);
    }

    @Bean
    public Queue handleBasketQueue() {
        return new Queue(BASKETBALL_QUEUE_NAME);
    }

    @Bean
    public Queue handleBookQueue() {
        return new Queue(BOOK_QUEUE_NAME);
    }

    @Bean
    public Queue handleObjectQueue() {
        return new Queue(HANDLER_OBJECT_QUEUE_NAME);
    }

    @Bean
    public TopicExchange handleExchange() {
        return new TopicExchange(EXCHANGE_TEST);
    }

    @Bean
    public Binding footballBind() {
        return BindingBuilder.bind(handleFootQueue()).to(handleExchange()).with(SPORT_TOPIC_NAME);
    }

    @Bean
    public Binding basketballBind() {
        return BindingBuilder.bind(handleBasketQueue()).to(handleExchange()).with(SPORT_TOPIC_NAME);
    }

    @Bean
    public Binding bookBind() {
        return BindingBuilder.bind(handleBookQueue()).to(handleExchange()).with(BOOK_TOPIC_NAME);
    }

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
}