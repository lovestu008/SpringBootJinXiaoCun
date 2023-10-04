package com.xxxx.supermarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class SupermarketApplication {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) {
        SpringApplication.run(SupermarketApplication.class, args);
    }

}
