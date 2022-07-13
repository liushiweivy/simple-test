package com.simple.multidatasource.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import com.simple.multidatasource.base.DataSource;

/**
 * @Author : JCccc
 * @CreateTime : 2019/10/23
 * @Description :
 **/
@Mapper
public interface DataSourceMapper {
 
    @Select("SELECT * FROM databasesource")
    List<DataSource> get();
 
 
}