package com.chuhsi.serviceorder.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.chuhsi.serviceorder.mapper")
public class OrderConfig {

}
