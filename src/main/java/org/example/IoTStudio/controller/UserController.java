package org.example.IoTStudio.controller;

import cn.hutool.core.lang.Dict;
import org.example.IoTStudio.model.bean.TbUser;
import org.example.IoTStudio.model.bean.CommonResult;
import org.example.IoTStudio.service.AddressService;
import org.example.IoTStudio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;

@Controller
@RestController //允许前端返回 Json 格式的数据
@RequestMapping("/user")
@Component
@Transactional(rollbackForClassName = "Exception.class")
public class UserController {

    private final UserService userService;
    //Authorization : Bearer TOKEN
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping("/register")
    public CommonResult<String> register(@RequestBody TbUser user) {
        System.out.println("--------------------req username");
        System.out.println(user.getUsername()+"");
        userService.userRegister(user);
        //user.setAddress(); 待添加获取地址
        return new CommonResult<>(20000, "OK", "注册成功");
    }

    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody TbUser user) {
        System.out.println("--------------------req username");
        System.out.println(user.getUsername()+"");
        String value = userService.userLogin(user).toString();
        System.out.println("--------------------token");
        return new CommonResult<>(20000, "OK", value+"");
    }



}
