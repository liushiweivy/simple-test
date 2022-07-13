package com.simple.multidatasource.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.simple.multidatasource.base.User;

/**
 * @Author : JCccc
 * @CreateTime : 2019/10/23
 * @Description :
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user")
    List<User> queryUserInfo();

}