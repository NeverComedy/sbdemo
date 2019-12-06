package com.sbdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/hello"})
public class HelloController {

    @RequestMapping(value = {"/sbdemo"})
    public String hello(){
        return "Hello";
    }
}
