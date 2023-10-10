package com.xxxx.supermarket.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Component
@Aspect
public class PermissionProxy {
    @Resource
    private HttpSession session;

    @Around(value = "@annotation(com.xxxx.supermarket.annotation.RequiredPermission)")
    public Object around(ProceedingJoinPoint pjp) {
        Object result = null;
        //得到当前登录用户拥有的权限
        List<String> permssions = (List<String>) session.getAttribute("permissions");




        return result;
    }

}
