package com.yogafire.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.yogafire.model.dao")
public class DBConfig {

}
