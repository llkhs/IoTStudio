package org.example.IoTStudio.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.ObjectUtils;
import org.example.IoTStudio.dao.UserDao;
import org.example.IoTStudio.model.bean.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.naming.Name;


@Service

@Transactional(rollbackForClassName = "Exception.class")
public class AddressService {

    @Autowired
    private UserDao userDao;

    public AddressService(UserDao userDao){
        super();
        this.userDao = userDao;
    }

    public String GetNewAddress(String username){
        try {
            if (userDao.checkUserName(username) != null) {
                System.out.println("--------------------check username exist");
                throw new DuplicateKeyException("已存在");
            }else {
                System.out.println("--------------------register new address");
                String baseUrl = "http://139.9.64.155:5002/WeBASE-Front/privateKey" +
                        "?signUserId=62588f303e5b429f8944aa732f96008e" +
                        "&appId=1" +
                        "&returnPrivateKey=false" +
                        "&type=0" +
                        "&userName=%s";
                String url = String.format(baseUrl, username);
                RestTemplate restTemplate = new RestTemplate();
                JSONObject jsonObject = JSON.parseObject(
                        restTemplate.exchange(
                                        url,
                                        HttpMethod.GET,
                                        null,
                                        String.class)
                                .getBody());
                assert jsonObject != null;
                String address = (String) jsonObject.get("address");
                return address;
            }
        }
        catch (Exception e){
            throw new DuplicateKeyException("用户已存在");
        }
    }
}
