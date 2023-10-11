package com.xxxx.supermarket.aspect;

import com.xxxx.supermarket.annotation.RequiredPermission;
import com.xxxx.supermarket.exceptions.AuthException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
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
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
        //得到当前登录用户拥有的权限
        List<String> permssions = (List<String>) session.getAttribute("permissions");
        //判断用户是否有权限
        if (null == permssions ||permssions.size() <1){
            throw new AuthException();
        }
        //得到对应目标
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        //得到对应的方法上的注解
        RequiredPermission requiredPermission = methodSignature.getMethod().getDeclaredAnnotation(RequiredPermission.class);
        if (!(permssions.contains(requiredPermission.code()))) {
            //如果权限中不包含当前方法上注解指定的权限码，则抛出异常
            throw new AuthException();
        }
        result = pjp.proceed();
        return result;
    }

}
