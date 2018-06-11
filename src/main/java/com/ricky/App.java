package com.ricky;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;

/**
 *
 */
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableScheduling
@MapperScan("com.ricky.mapper")

public class App {
    public static void main(String[] args) {
        /*HashMap<String,String> hashmap= new HashMap<>();*/
        SpringApplication.run(App.class, args);
    }
}
