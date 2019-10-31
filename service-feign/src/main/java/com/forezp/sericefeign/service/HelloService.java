package com.forezp.sericefeign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Title: HelloService
 * @Description: feign接口
 * @Author: ccg
 * @Date: 2019/6/20 16:47
 *
 * @ FeignClient（“服务名”），来指定调用哪个服务
 * fallback属性指定熔断的快速失败方法
 */
@FeignClient(value = "service-hi",fallback = ErrorHandler.class)
public interface HelloService {

    /**
     * 从SERVICE-CLIENT服务器的/hi接口获取JSON数据
     *
     * @param name
     * @return
     */
    @RequestMapping("/hi")
    String obtainOtherServerJsonData(@RequestParam(value = "name") String name);

}
