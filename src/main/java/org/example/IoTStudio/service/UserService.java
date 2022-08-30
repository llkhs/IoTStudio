package org.example.IoTStudio.service;


import org.example.IoTStudio.dao.UserDao;
import org.example.IoTStudio.model.bean.TbUser;
import org.example.IoTStudio.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


@EnableAsync
@Service
@Transactional(rollbackForClassName = "Exception.class")
public class UserService{
    //通过构造方法注入

    @Autowired
    private final UserDao userDao;
    private final JwtUtil jwtUtil;
    @Autowired
    private AddressService addressService;
    public UserService(UserDao userDao, JwtUtil jwtUtil,AddressService addressService) {
        super();
        this.userDao = userDao;
        this.jwtUtil = jwtUtil;
        this.addressService=addressService;
    }



    public void userRegister(TbUser user) {
        try {
            System.out.println("--------------------register new user");
            String address = addressService.GetNewAddress(user.getUsername());
            System.out.println(address);
            //user.setUid(idWorker.nextId());
            user.setAddress(address);
            user.setFlag(1);
            //user.setPasswd(bCryptPasswordEncoder.encode(user.getPasswd()));
            userDao.userRegister(user);
        } catch (Exception e) {
            throw new DuplicateKeyException("用户名已存在");
        }
    }

    public Map<String,String> userLogin(TbUser user) {
        System.out.println("--------------------login username");
        System.out.println(user.getUsername()+"");
        try {
            TbUser trueUserInfo = userDao.userLogin(user.getUsername());
            if (trueUserInfo.getPasswd().equals(user.getPasswd())){
                Map<String,String> map = new HashMap<String,String>();
                map.put("token", "Bearer " + jwtUtil.createJWT(trueUserInfo.getUsername()+"",trueUserInfo.getPasswd()+""));
                map.put("address",trueUserInfo.getAddress()+"");
                System.out.println(map.get("token"));
                System.out.println(map.get("address"));
                return map;
            }else {
                throw new InternalAuthenticationServiceException("用户名或密码有误");
            }
        }
        catch (Exception e){
            throw new InternalAuthenticationServiceException("用户名或密码有误");
        }
    }


}
