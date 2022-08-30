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


@EnableAsync
@Service
@Transactional(rollbackForClassName = "Exception.class")
public class UserService{
    //通过构造方法注入

    @Autowired
    private final UserDao userDao;
    private final JwtUtil jwtUtil;

    public UserService(UserDao userDao, JwtUtil jwtUtil) {
        super();
        this.userDao = userDao;
        this.jwtUtil = jwtUtil;
    }



    public void userRegister(TbUser user) {
        try {
            //user.setUid(idWorker.nextId());
            user.setFlag(1);

            //user.setAddtime(DateTimeTransferUtil.getNowTimeStamp());
            //user.setPasswd(bCryptPasswordEncoder.encode(user.getPasswd()));
            userDao.userRegister(user);
        } catch (Exception e) {
            throw new DuplicateKeyException("用户名已存在");
        }
    }

    public String userLogin(TbUser user) {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@4");
        System.out.println(user.getUsername()+"");
        TbUser trueUserInfo = userDao.userLogin(user.getUsername());

        if (trueUserInfo.getPasswd().equals(user.getPasswd())){
            return jwtUtil.createJWT(trueUserInfo.getUsername()+"",trueUserInfo.getUsername()+"");
        }
        throw new InternalAuthenticationServiceException("用户名或密码有误");
    }


}
