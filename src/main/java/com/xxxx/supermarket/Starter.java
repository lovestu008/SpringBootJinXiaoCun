package com.xxxx.supermarket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xxxx.supermarket.dao")
public class Starter {
    public static void main(String[] args) {
        SpringApplication.run(Starter.class);
    }
}
