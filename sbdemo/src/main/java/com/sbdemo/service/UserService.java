package com.sbdemo.service;

import com.sbdemo.entity.User;

public interface UserService {

    //用户登录
    User userLogin(String username, String password);

    //新用户注册
    User register(User user);

    //用户基本信息修改
    int userUpdateInfo(String sex,int age,String username);
}
