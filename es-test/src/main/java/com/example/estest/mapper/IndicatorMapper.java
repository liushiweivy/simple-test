package com.example.estest.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.estest.entity.ApiLog;
import com.example.estest.entity.Indicator;

/**
 * @author simple
 * @date 2021/10/3 22:40
 */
@Mapper
public interface IndicatorMapper extends BaseMapper<Indicator> {
}
