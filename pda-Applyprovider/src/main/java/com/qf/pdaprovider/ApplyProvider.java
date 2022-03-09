package com.qf.pdaprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ：lh
 * @date ：Created in 2022/3/7 16:25
 */
@EnableEurekaClient
@SpringBootApplication
public class ApplyProvider {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ApplyProvider.class,args);
//        while (true){Thread.sleep(Integer.MAX_VALUE);}
    }
}
