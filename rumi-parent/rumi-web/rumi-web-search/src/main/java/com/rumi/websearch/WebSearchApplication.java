package com.rumi.websearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName: WebSearchApplication
 * @Description:
 * @Author: CSH
 * @Date: 2025-05-16 22:00
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.rumi.search.feign")
public class WebSearchApplication {
        public static void main(String[] args) {
            SpringApplication.run(WebSearchApplication.class,args);
        }


}
