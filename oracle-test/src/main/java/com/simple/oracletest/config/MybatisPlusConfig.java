package com.simple.oracletest.config;

import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ljp
 * @date 2020/4/23 22:14
 */
@Configuration
@MapperScan("com.simple.oracletest.dao")
public class MybatisPlusConfig {

    @Bean
    public OracleKeyGenerator oracleKeyGenerator() {
        return new OracleKeyGenerator();
    }
}

