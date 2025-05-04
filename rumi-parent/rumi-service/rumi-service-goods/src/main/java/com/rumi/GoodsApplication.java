package com.rumi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName: GoodsApplication
 * @Description:
 * @Author: CSH
 * @Date: 2025-05-04 15:15
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.rumi.dao")
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class,args);
    }
}
