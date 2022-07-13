package com.simple.multidatasource.base;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
 
/**
 * @Author : JCccc
 * @CreateTime : 2019/10/23
 * @Description :
 **/
 
@RestController
public class UserController {
 
 
    @Autowired
    private DBChangeService dbChangeServiceImpl;

    @Autowired
    UserService userService;
 
 
 
 
    /**
     * 查询所有
     * @return
     */
    @GetMapping("/test")
    public  String test() throws Exception {
 
        //切换到数据库dbtest2
        String datasourceId="dbtest2";
        dbChangeServiceImpl.changeDb(datasourceId);
        List<User> userList= userService.queryUserInfo();
        System.out.println(userList.toString());
 
        //再切换到数据库dbtest3
        dbChangeServiceImpl.changeDb("dbtest3");
        List<User> userList3= userService.queryUserInfo();
        System.out.println(userList3.toString());
 
 
        //切回主数据源
        DBContextHolder.clearDataSource();
        return "ok";
    }

    @PostMapping("/register")
    public  int register(@Valid UserDTO userDTO) throws Exception {

        //切换到数据库dbtest2
        String datasourceId="dbtest2";
        dbChangeServiceImpl.changeDb(datasourceId);
    return  userService.register(userDTO);

    }

    @PostMapping("/check")
    public  boolean check(String username,String password) throws Exception {
        //切换到数据库dbtest2
        String datasourceId="dbtest2";
        dbChangeServiceImpl.changeDb(datasourceId);
        return  userService.check(username,password);

    }
 
}