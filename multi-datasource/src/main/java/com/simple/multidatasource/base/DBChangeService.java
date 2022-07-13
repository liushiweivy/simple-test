package com.simple.multidatasource.base;

import java.util.List;
 
/**
 * @Author : JCccc
 * @CreateTime : 2019/10/22
 * @Description :
 **/
 
public interface DBChangeService {
 
    List<DataSource> get();
 
    boolean changeDb(String datasourceId) throws Exception;
 
}