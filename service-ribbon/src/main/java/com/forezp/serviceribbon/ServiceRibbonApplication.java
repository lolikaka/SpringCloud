package com.forezp.serviceribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 服务消费者
 * ribbon是一个负载均衡客户端，可以很好的控制htt和tcp的一些行为（分发请求）
 *
 * @EnableDiscoveryClient 向服务中心注册；并且向程序的ioc注入一个bean: restTemplate;
 *
 * @LoadBalanced 注解表明这个restRemplate开启负载均衡的功能
 *
 * 在微服务架构中，根据业务来拆分成一个个的服务，服务与服务之间可以相互调用（RPC），
 * 在Spring Cloud可以用RestTemplate+Ribbon和Feign来调用。为了保证其高可用，
 * 单个服务通常会集群部署。由于网络原因或者自身的原因，服务并不能保证100%可用，
 * 如果单个服务出现问题，调用这个服务就会出现线程阻塞，此时若有大量的请求涌入，
 * Servlet容器的线程资源会被消耗完毕，导致服务瘫痪。服务与服务之间的依赖性，
 * 故障会传播，会对整个微服务系统造成灾难性的严重后果，这就是服务故障的“雪崩”效应。
 * 为了解决这个问题，业界提出了断路器模型。
 *
 * 当对特定的服务的调用的不可用达到一个阀值（Hystric 是5秒20次） 断路器将会被打开。
 * 断路打开后，可用避免连锁故障，fallback方法可以直接返回一个固定值。
 *
 * @EnableHystrix 注解开启Hystrix(熔断)
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableHystrix
public class ServiceRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRibbonApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
