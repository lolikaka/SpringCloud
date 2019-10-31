package com.config.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @EnableConfigServer 注解开启配置服务器的功能
 *
 * @RefreshScope 配置文件自动更新
 * 注解@EnableEurekaClient上有@EnableDiscoveryClient注解，
 * 可以说基本就是EnableEurekaClient有@EnableDiscoveryClient的功能，
 * 另外上面的注释中提到，其实@EnableEurekaClientz注解就是一种方便使用eureka的注解而已，
 * 可以说使用其他的注册中心后，都可以使用@EnableDiscoveryClient注解，
 * 但是使用@EnableEurekaClient的情景，就是在服务采用eureka作为注册中心的时候，
 * 使用场景较为单一。
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
// @RefreshScope
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

}
