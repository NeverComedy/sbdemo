package com.sbdemo.controller;

import com.sbdemo.entity.User;
import com.sbdemo.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = {"/user"})
public class UserLoginController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    //首页
    @RequestMapping(value = {"/indexPage"})
    public String index(){
        return "Index";
    }

    //访问登录页面路径
    @RequestMapping(value = {"/loginPage"})
    public String userLogin(){
        return "/user/UserLoginPage";
    }

    //访问注册页面路径
    @RequestMapping(value = {"/registerPage"})
    public String userRegister(){
        return "/user/UserRegisterPage";
    }

    //访问个人信息修改路径
    @RequestMapping(value ={"/updateUserInfoPage"})
    public String updataUserInfo(){
        return "/user/UpdateUserInfoPage";
    }

    @RequestMapping(value = {"/userLogin"})
    public String userLogin(@RequestParam("username")String username,@RequestParam("password")String password,HttpServletRequest request){
        User user = userServiceImpl.userLogin(username,password);
        if(user != null){
            request.getSession().setAttribute("username",user.getUsername());
            System.out.print("getUsername:"+user.getUsername());
            return "Index";
        }
        return "Error";
    }

    @RequestMapping(value ={"/userRegiser"})
    public String userRegister(User user){
        User u = userServiceImpl.register(user);
        System.out.print("result:"+u);
        if(u != null){
            return "Index";
        }
        return "Error";
    }

    @RequestMapping(value = {"/userUpdateInfo"})
    public String userUpdateInfo(@RequestParam("sex")String sex,@RequestParam("age")int age, HttpServletRequest request){
        Object obj =request.getSession().getAttribute("username");
        System.out.print("obj:"+obj);
        String username = (String) obj;
        System.out.print("username:"+username);
        int result = userServiceImpl.userUpdateInfo(sex,age,username);
        if(result > 0){
            return "Index";
        }
        return "Error";
    }

}
