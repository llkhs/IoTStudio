package org.example.IoTStudio.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.IoTStudio.entity.TbUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    @Insert("INSERT INTO tb_user(uid,username,passwd,address,author,flag)" +
            "VALUES(#{user.uid},#{user.username},#{user.passwd},#{user.address},#{user.author},#{user.flag})")
    void userRegister(@Param("user") TbUser user);


    @Select("SELECT uid,passwd FROM tb_user WHERE username = #{loginName}")
    TbUser userLogin(@Param("loginName") String loginName);


}
