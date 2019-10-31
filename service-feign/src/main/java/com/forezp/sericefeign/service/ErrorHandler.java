package com.forezp.sericefeign.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Title: ErrorHandler
 * @Description: 熔断方法
 * @Author: ccg
 * @Date: 2019/6/20 17:25
 *
 * fegin的熔断方法需要实现SchedualServiceHi 接口，并注入到Ioc容器中
 */
@Component
public class ErrorHandler implements HelloService {

    @Value("${server.port}")
    private String port;

    @Override
    public String obtainOtherServerJsonData(String name) {
        return "sorry " + name + " , " + port + " server internal error";
    }

}
