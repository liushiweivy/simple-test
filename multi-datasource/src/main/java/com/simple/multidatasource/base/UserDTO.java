package com.simple.multidatasource.base;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Author : JCccc
 * @CreateTime : 2019/10/22
 * @Description :
 **/

@Data
public class UserDTO {

    @NotBlank
    String userName;
    String age;
    @NotBlank
    String password;
    String salt;
}
