package org.example.IoTStudio.controller;

import org.example.IoTStudio.entity.TbUser;
import org.example.IoTStudio.model.bean.CommonResult;
import org.example.IoTStudio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController //允许前端返回 Json 格式的数据
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    //Authorization : Bearer TOKEN
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping("/register")
    public CommonResult<String> register(@RequestBody TbUser user) {
        System.out.println(user.getUsername());
        userService.userRegister(user);
        //user.setAddress(); 待添加获取地址
        return new CommonResult<>(20000, "OK", "注册成功");
    }

    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody TbUser user) {
        String token = userService.userLogin(user);
        return new CommonResult<>(20000, "OK", token);
    }





}
