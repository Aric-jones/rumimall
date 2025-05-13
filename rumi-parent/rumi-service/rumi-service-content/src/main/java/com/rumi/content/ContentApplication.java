package com.rumi.content;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName: ContentApplication
 * @Description:
 * @Author: CSH
 * @Date: 2025-05-13 19:57
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.rumi.content.dao"})
public class ContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentApplication.class);
    }

}
