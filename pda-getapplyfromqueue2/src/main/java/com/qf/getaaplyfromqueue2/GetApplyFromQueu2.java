package com.qf.getaaplyfromqueue2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ：lh
 * @date ：Created in 2022/3/15 9:35
 */
@EnableEurekaClient
@SpringBootApplication
public class GetApplyFromQueu2 {
    public static void main(String[] args) {
        SpringApplication.run(GetApplyFromQueu2.class,args);
    }


}
