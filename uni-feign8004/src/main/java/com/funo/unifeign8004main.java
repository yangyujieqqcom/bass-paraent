package com.funo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class unifeign8004main {
    public static void main(String[] args) {
        SpringApplication.run(unifeign8004main.class,args);
    }

}
