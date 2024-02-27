package com.huang.luck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableCaching
@SpringBootApplication
public class LuckApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuckApplication.class, args);
    }

}
