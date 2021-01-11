package com.simon.magiccube.web;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackages = {"com.simon.magiccube"})
@ServletComponentScan("com.simon.magiccube")
@MapperScan(value = "com.simon.magiccube.dao.mapper")
@EnableTransactionManagement(proxyTargetClass = true)
public class MagicCubeWebApplication implements WebMvcConfigurer {
    private static Logger LOG = LoggerFactory.getLogger(MagicCubeWebApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MagicCubeWebApplication.class, args);
        LOG.info("magiccube SpringBoot Start Success");

    }
}