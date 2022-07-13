package com.example.estest.entity;

import java.util.Date;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author simple
 * @date 2021/8/19 23:12
 */
@Data
@TableName
public class IndicatorRecord {

	private Integer id;

	private Integer indicatorId;

	private Integer count;

	private Date generateTime;

}
