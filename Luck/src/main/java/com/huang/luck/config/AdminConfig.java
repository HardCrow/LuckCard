package com.huang.luck.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdminConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //访问的路径    当访问/upload/**路径下，会去Locations的路径文件上找
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:D:/userPhoto/upload/");

    }
}

