package com.qf.getapplyfromqueue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ：lh
 * @date ：Created in 2022/3/14 14:14
 */
@EnableEurekaClient
@SpringBootApplication
public class GetApplyFromQueue {
    public static void main(String[] args) {
        SpringApplication.run(GetApplyFromQueue.class, args);
    }

}
