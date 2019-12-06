package com.sbdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//切面注解
@Aspect
//放入Spring扫描类中
@Component
public class HttpAspect {

    //使用Spring自带的日志打印
    private final static Logger logger = LoggerFactory.getLogger(HttpSession.class);

    //aspect拦截方法的相对路径
    @Pointcut("execution(public * com.sbdemo.controller.UserLoginController.*(..))")
    public void log(){
    }

    //方法之前拦截
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes  =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //获取url
        logger.info("url={}", request.getRequestURL());
        //method
        logger.info("method={}", request.getMethod());
        //ip
        logger.info("ip={}", request.getRemoteAddr());
        //类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //参数
        logger.info("agrs={}", joinPoint.getArgs());

    }

    //方法之后拦截
    @After("log()")
    public void doAfter(){
        logger.info("222222");
    }

    //返回输出信息
    @AfterReturning(returning = "object" , pointcut = "log()")
    public void doAfterReturning(Object object){
        logger.info("response={}", object.toString());
    }
}
