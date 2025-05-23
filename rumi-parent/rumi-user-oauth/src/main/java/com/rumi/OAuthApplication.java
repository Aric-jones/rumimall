package com.rumi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/*****
 * @Author: www.itheima
 * @Date: 2019/7/6 8:01
 * @Description: com.changgou
 ****/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.rumi.auth.dao")
@EnableFeignClients(basePackages = "com.rumi.user.feign")
public class OAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuthApplication.class,args);
    }


    @Bean(name = "restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}