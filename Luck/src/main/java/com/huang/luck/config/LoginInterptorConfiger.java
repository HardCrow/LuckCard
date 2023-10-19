package com.huang.luck.config;

import com.huang.luck.interceptor.LoginInterptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoginInterptorConfiger implements WebMvcConfigurer {


    //将自定义的拦截器进行注册
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建自定义拦截器的对象
        HandlerInterceptor interceptor = new LoginInterptor();
        List<String> patterns=new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/js/**");
        patterns.add("/image/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/produce.html");
        patterns.add("/users/reg");
        patterns.add("/users/get_by_uid");
        patterns.add("/users/login");
        //完成拦截器的注册
        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(patterns);

    }
}


