package com.qf.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**配置中心启动类
 * @author ：lh
 * @date ：Created in 2022/3/11 9:45
 */
//@EnableEurekaClient
@SpringBootApplication
@EnableConfigServer//配置中心服务
public class ConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class,args);
    }
}
