package com.xxxx.supermarket.aspect;

import com.xxxx.supermarket.dao.LogMapper;
import com.xxxx.supermarket.entity.Log;
import com.xxxx.supermarket.utils.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
@Aspect
@Slf4j
public class SupLogAop implements Ordered {
    @Resource
    private LogMapper logMapper;
    /**
     * 定义SupLogAop的切入点为标记@SupLog注解的方法
     */
    @Pointcut(value = "@annotation(com.xxxx.supermarket.aspect.SupLog)")
    public void pointcut() {
    }
    /**
     * 业务操作环绕通知
     *
     * @param proceedingJoinPoint
     * @retur
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("----SupAop 环绕通知 start");
        //执行目标方法
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        //目标方法执行完成后，获取目标类、目标方法上的业务日志注解上的功能名称和功能描述
        Object target = proceedingJoinPoint.getTarget();
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        SupLog anno1 = target.getClass().getAnnotation(SupLog.class);
        SupLog anno2 = signature.getMethod().getAnnotation(SupLog.class);
        if (anno1!=null&&anno2!=null){
            Log log = new Log();
            String logType= anno1.type();
            String logContent = anno2.content();
            log.setType(logType);
            log.setContent(logContent);
            log.setUname("admin");
            log.setId(1);
            log.setTime(new Date());
            //保存业务操作日志信息
            AssertUtil.isTrue(logMapper.insertSelective(log)<1,"保存操作日志失败！");
        }
        log.info("----SupAop 环绕通知 end");
        return result;
    }
    @Override
    public int getOrder() {
        return 1;
    }
}