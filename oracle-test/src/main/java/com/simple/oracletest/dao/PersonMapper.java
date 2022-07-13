package com.simple.oracletest.dao;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.simple.oracletest.entity.Person;

/**
 * @author simple
 * @date 2021/5/15 14:51
 */
@Mapper
public  interface PersonMapper extends BaseMapper<Person> {
}
