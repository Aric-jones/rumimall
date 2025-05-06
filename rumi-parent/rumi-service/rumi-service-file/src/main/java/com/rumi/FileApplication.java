package com.rumi;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName: FileApplication
 * @Description:
 * @Author: CSH
 * @Date: 2025-05-06 14:57
 */
// 排除数据库自动加载
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
public class FileApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(FileApplication.class,args);
    }
}
