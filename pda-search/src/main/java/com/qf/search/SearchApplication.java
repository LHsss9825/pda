package com.qf.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ：lh
 * @date ：Created in 2022/3/18 13:26
 */
//@EnableFeignClients
//@EnableEurekaClient
@SpringBootApplication
//@EnableCircuitBreaker //开启hystrix的使用
////@ServletComponentScan(basePackages = "com.qf.search.filter")
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class,args);
    }
}
