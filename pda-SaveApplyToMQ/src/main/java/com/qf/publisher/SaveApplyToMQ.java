package com.qf.publisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ：lh
 * @date ：Created in 2022/3/14 13:33
 */
@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient
public class SaveApplyToMQ {
    public static void main(String[] args) {
        SpringApplication.run(SaveApplyToMQ.class,args);
    }

}
