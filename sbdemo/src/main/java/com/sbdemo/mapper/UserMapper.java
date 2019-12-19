package com.sbdemo.mapper;

import com.sbdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    //用户登录
    User userLogin(@Param("username") String username, @Param("password") String password);

    //用户注册
    int register(User user);

    //用户基本信息修改
    int userUpdateInfo(@Param("sex")String sex,@Param("age")int age,@Param("username")String username);

    //用户信息查询
    User userInfo(@Param("username")String username);
}