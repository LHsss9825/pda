package com.qf.pdaconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ：lh
 * @date ：Created in 2022/3/7 16:13
 */
@EnableEurekaClient
@SpringBootApplication
public class ApplyConsumer {
    public static void main(String[] args) {
        SpringApplication.run(ApplyConsumer.class,args);
    }
}
