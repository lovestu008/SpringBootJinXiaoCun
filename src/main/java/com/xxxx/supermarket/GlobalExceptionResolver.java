package com.xxxx.supermarket;

import com.alibaba.fastjson.JSON;
import com.xxxx.supermarket.base.ResultInfo;
import com.xxxx.supermarket.exceptions.AuthException;
import com.xxxx.supermarket.exceptions.NoLoginException;
import com.xxxx.supermarket.exceptions.ParamsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    /**
    * ⽅法返回值类型
    * 视图
    * JSON
    * 如何判断⽅法的返回类型：
    * 如果⽅法级别配置了 @ResponseBody 注解，表示⽅法返回的是JSON；
    * 反之，返回的是视图⻚⾯
    * @param request
    * @param response
    * @param handler
    * @param ex
    * @return
    */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        /**
         * 判断异常类型
         * 如果是未登录异常，则先执⾏相关的拦截操作
         */
        if (ex instanceof NoLoginException){
            //如果捕获到的是未登录异常，则重定向到登录页面
            ModelAndView mv = new ModelAndView("redirect:/index");
            return mv;
        }
        // 设置默认异常处理
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("code",500);
        modelAndView.addObject("msg","系统异常，请稍后再试...");
        //判断HandlerMethod
        if (handler instanceof HandlerMethod){
            //类型转换
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //获取方法上的RequestBody注解
            ResponseBody responseBody =handlerMethod.getMethod().getDeclaredAnnotation(ResponseBody.class);
            // 判断 ResponseBody 注解是否存在 (如果不存在，表示返回的是视图;如果存在，表示返回的是JSON)
            if (null == responseBody){
                if (ex instanceof ParamsException){
                    ParamsException pe = (ParamsException) ex;
                    modelAndView.addObject("code",pe.getCode());
                    modelAndView.addObject("msg",pe.getMsg());
                }else if (ex instanceof AuthException){//认证异常
                    AuthException a = (AuthException) ex;
                    //设置异常信息
                    modelAndView.addObject("code",a.getCode());
                    modelAndView.addObject("msg",a.getMsg());
                }
                return modelAndView;
            }else {
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setCode(300);
                resultInfo.setMsg("系统异常，请重试 ");
                //如果捕获的是自定义异常
                if (ex instanceof ParamsException){
                    ParamsException pe = (ParamsException) ex;
                    resultInfo.setCode(pe.getCode());
                    resultInfo.setMsg(pe.getMsg());
                } else if (ex instanceof AuthException){//认证异常
                    AuthException a = (AuthException) ex;
                    resultInfo.setCode(a.getCode());
                    resultInfo.setMsg(a.getMsg());
                }
                //设置相应类型和编码格式
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = null;
                try{
                    out = response.getWriter();
                    //将对象转换成json格式，通过输出流输出
                    out.write(JSON.toJSONString(resultInfo));
                    out.flush();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (out != null){
                        out.close();
                    }
                }
                return null;
            }

        }
        return modelAndView;
    }
}
