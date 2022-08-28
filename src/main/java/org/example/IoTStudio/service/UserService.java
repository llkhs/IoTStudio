package org.example.IoTStudio.service;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.example.IoTStudio.dao.UserDao;
import org.example.IoTStudio.entity.TbUser;
import org.example.IoTStudio.util.IdWorker;
import org.example.IoTStudio.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@NoArgsConstructor
@Data
@Transactional(rollbackForClassName = "Exception.class")
public class UserService {
    //通过构造方法注入

    UserDao userDao;
    IdWorker idWorker =new IdWorker();
    private JwtUtil jwtUtil;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserService(UserDao userDao, IdWorker idWorker, JwtUtil jwtUtil, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.idWorker = idWorker;
        this.jwtUtil = jwtUtil;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void userRegister(TbUser user) {
        try {
            user.setUid(idWorker.nextId());
            user.setFlag(1);

            //user.setAddtime(DateTimeTransferUtil.getNowTimeStamp());
            user.setPasswd(bCryptPasswordEncoder.encode(user.getPasswd()));
            //userDao.userRegister(user);
        } catch (Exception e) {
            throw new DuplicateKeyException("用户名重复");
        }
    }

    public String userLogin(TbUser user) {
        TbUser trueUserInfo = userDao.userLogin(user.getUsername());
        String trueUserPasswd = trueUserInfo.getPasswd();
        if (bCryptPasswordEncoder.matches(trueUserPasswd,user.getPasswd())){
            return jwtUtil.createJWT(trueUserInfo.getUid()+"",trueUserInfo.getUsername());
        }
        throw new InternalAuthenticationServiceException("用户名或密码有误");
    }


}
