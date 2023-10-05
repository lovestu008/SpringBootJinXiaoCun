package com.xxxx.supermarket.config;

import com.xxxx.supermarket.interceptors.NoLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Bean
    public NoLoginInterceptor noLoginInterceptor() {
        return new NoLoginInterceptor();
    }

    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 需要⼀个实现HandlerInterceptor接⼝的拦截器实例，这⾥使⽤的是 NoLoginInterceptor
        registry.addInterceptor(noLoginInterceptor())
                // ⽤于设置拦截器的过滤路径规则
                .addPathPatterns("/**")
                // ⽤于设置不需要拦截的过滤规则
                .excludePathPatterns("/index", "/user/login", "/css/**", "/images/**", "/js/**", "/lib/**");
    }
}
