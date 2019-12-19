package com.controller;

import com.entity.User;
import com.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = {"/user"})
public class UserLoginController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    //访问主页面
    @RequestMapping(value = {"/indexPage"})
    public String index(){
        return "Index";
    }

    //访问登录页面路径
    @RequestMapping(value = {"/loginPage"})
    public String userLogin(){
        return "/user/UserLoginPage";
    }

    //访问注册页面
    @RequestMapping(value = {"/registerPage"})
    public String userRegister(){
        return "/user/UserRegisterPage";
    }

    //访问用户信息修改页面
    @RequestMapping(value = {"/updatePage"})
    public String userUpdateInfo(){
        return "/user/UpdateUserInfoPage";
    }

    //用户信息显示
    @RequestMapping(value = {"/userInfo"})
    public String userInfo(Model model , HttpServletRequest request){
        Object requsername = request.getSession().getAttribute("username");
        String username = (String) requsername;
        User user = userServiceImpl.userInfo(username);
        //System.out.println("用户信息:"+user.toString());
        if(user != null){
            model.addAttribute("user",user);
            return "/user/UserInfoPage";
        }
        return "Error";
    }

    //用户登录
    @RequestMapping(value = {"/userLogin"})
    public String userLogin(Model model, @RequestParam("username")String username,@RequestParam("password")String password,HttpServletRequest request){
        User user = userServiceImpl.userLogin(username,password);
        if(user != null){
            request.getSession().setAttribute("username",user.getUsername());
            model.addAttribute("user",user);
            return "Index";
        }
        return "Error";
    }

    //用户注册
    @RequestMapping(value ={"/userRegiser"})
    public String userRegister(User user,HttpServletRequest request){
        int u = userServiceImpl.userRegister(user);
        if(u > 0){
            request.getSession().setAttribute("username",user.getUsername());
            return "Index";
        }
        return "Error";
    }

    //用户信息更改
    @RequestMapping(value = {"/userUpdateInfo"})
    public String userUpdateInfo(@RequestParam("sex")String sex,@RequestParam("age")int age, HttpServletRequest request){
        Object obj =request.getSession().getAttribute("username");
        //System.out.print("obj:"+obj);
        String username = (String) obj;
        //System.out.print("username:"+username);
        int result = userServiceImpl.userUpdateInfo(sex,age,username);
        if(result > 0){
            request.getSession().setAttribute("username",username);
            return "Index";
        }
        return "Error";
    }

}
