package com.qf.pdaconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author ：lh
 * @date ：Created in 2022/3/7 16:13
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EnableCircuitBreaker //开启hystrix的使用
@ServletComponentScan(basePackages = "com.qf.pdaconsumer.filter")
public class ApplyConsumer {
    public static void main(String[] args) {
        SpringApplication.run(ApplyConsumer.class, args);

        }
        //开启轮循机制
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
