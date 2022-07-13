package com.example.estest.entity;

import lombok.Data;

/**
 * @author simple
 * @date 2021/7/24 11:59
 */
@Data
public class EsQueryParams {

	Integer start ;
	Integer limit ;
	String keyWord ;
	Integer minBalance ;
	Integer maxBalance ;
	String address ;
	String city ;
	String firstname ;
	String employer ;
	String userId ;
}
