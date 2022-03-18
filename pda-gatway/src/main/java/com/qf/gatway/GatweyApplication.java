package com.qf.gatway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ：lh
 * @date ：Created in 2022/3/10 14:35
 */
@SpringBootApplication
@EnableEurekaClient
public class GatweyApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatweyApplication.class,args);
    }
}
