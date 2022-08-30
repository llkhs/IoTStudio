package org.example.IoTStudio.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import org.example.IoTStudio.model.bean.TbUser;

@Mapper
public interface UserDao {

    @Insert("INSERT INTO tb_user(uid,username,passwd,address,author,flag)" +
            "VALUES(#{user.uid},#{user.username},#{user.passwd},#{user.address},#{user.author},#{user.flag})")
    public void userRegister(@Param("user") TbUser user);


    @Select("" + "SELECT uid,username,passwd,address FROM tb_user WHERE username = #{loginName}" + "")
    TbUser userLogin(@Param("loginName") String loginName);

    @Select("" + "SELECT * address FROM tb_user WHERE username = #{username}" + "")
    TbUser checkUserName(@Param("username") String username);

}
