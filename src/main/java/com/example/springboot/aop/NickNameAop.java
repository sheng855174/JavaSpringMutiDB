package com.example.springboot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
@Aspect
public class NickNameAop {

    // 放入nickname
    @Before("execution(* tw.sheng.web.*.*(..))")
    public void doBeforeMethodPutNickName(JoinPoint joinPoint) {
        Object[] obj = joinPoint.getArgs();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Model model = null;
        for (Object argItem : obj) {
            if (argItem instanceof Model) {
                model = (Model)argItem;
            }
        }

    }


}
