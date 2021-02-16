package com.toggle.study.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan(basePackages = "com.toggle.study.mapper")
public class MybatisConfiguration {

}
