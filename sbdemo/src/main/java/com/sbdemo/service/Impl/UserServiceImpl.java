package com.sbdemo.service.Impl;

import com.sbdemo.entity.User;
import com.sbdemo.mapper.UserMapper;
import com.sbdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    //注入dao
    @Autowired
    private UserMapper userMapper;

    //用户登录
    public User userLogin(String username, String password){
        User user = userMapper.userLogin(username,password);
        return user;
    }

    //注册用户
    public User register(User user){
        System.out.println("User"+user);
        return userMapper.register(user);
    }

    //用户基本信息修改
    public int userUpdateInfo(String sex,int age,String username){
        return userMapper.userUpdateInfo(sex,age,username);
    }
}
