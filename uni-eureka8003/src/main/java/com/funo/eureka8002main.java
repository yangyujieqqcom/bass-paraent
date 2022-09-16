package com.funo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka服务端
 */
@SpringBootApplication
@EnableEurekaServer
public class eureka8002main {
    public static void main(String[] args) {
        SpringApplication.run(eureka8002main.class,args);
    }
}
