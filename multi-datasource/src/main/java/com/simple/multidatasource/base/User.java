package com.simple.multidatasource.base;

import lombok.Data;
import lombok.ToString;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Author : JCccc
 * @CreateTime : 2019/10/22
 * @Description :
 **/

@Data
@ToString
@TableName("user")
public class User {

    String userName;
    String age;
    String password;
    String salt;
}
