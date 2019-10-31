package com.forezp.serviceribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Title: HelloService
 * @Description: TODO
 * @Author: ccg
 * @Date: 2019/6/20 15:14
 */

@Service
public class HelloService {

    /**
     * 将IOC容器中的负载均衡RestTemplate工具注入进来
     */
    @Autowired
    RestTemplate restTemplate;

    /**
     * 通过调用restTemplate.getForObject(“http://SERVICE-HI/hi?name=”+name,String.class)方法时，
     * 已经做了负载均衡，访问了不同的端口的服务实例。
     *
     * 在方法上加上@HystrixCommand注解。该注解对该方法创建了熔断器的功能，并指定了fallbackMethod熔断方法，
     * 当 service-hi 工程不可用的时候，service-ribbon调用 service-hi的API接口时，会执行快速失败，
     * 直接返回一组字符串，而不是等待响应超时，这很好的控制了容器的线程阻塞。
     * @param name
     * @return
     */
    @HystrixCommand(fallbackMethod = "hiError")
    public String obtainOtherServiceData(String name) {
        //尝试调用其它微服务接口，访问的是SERVICE-HI这个服务器的/hi 接口
        //在这里我们直接用的程序名替代了具体的url地址，在ribbon中它会根据服务名来选择具体的服务实例，
        // 根据服务实例在请求的时候会用具体的url替换掉服务名
        return restTemplate.getForObject("http://SERVICE-HI/hi?name=" + name, String.class);
    }

    /***
    * @Description: 熔断方法
    * @Param: [name]
    * @Return: java.lang.String
    * @Author: ccg
    * @Date: 2019/10/5 20:47
    */
    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }

}
