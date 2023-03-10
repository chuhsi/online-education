package com.chuhsi.servicesms;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients    //服务调用
@ComponentScan("com.chuhsi")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class,args);
    }
}
