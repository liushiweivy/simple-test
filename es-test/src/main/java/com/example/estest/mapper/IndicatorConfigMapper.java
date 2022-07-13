package com.example.estest.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.estest.entity.Indicator;
import com.example.estest.entity.IndicatorConfig;

/**
 * @author simple
 * @date 2021/10/3 22:40
 */
@Mapper
public interface IndicatorConfigMapper extends BaseMapper<IndicatorConfig> {
}
