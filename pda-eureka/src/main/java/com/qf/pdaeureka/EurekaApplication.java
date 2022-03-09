package com.qf.pdaeureka;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import org.apache.tomcat.util.digester.Rule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;

/**
 * @author ：lh
 * @date ：Created in 2022/3/7 15:51
 */
@SpringBootApplication
@EnableEurekaServer //此服务是注册中心服务
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class,args);

    }
    @Bean
    public IRule iRule(){
        return  new RandomRule();//开启轮询机制；
    }
}
