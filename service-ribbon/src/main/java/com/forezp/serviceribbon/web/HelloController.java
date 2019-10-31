package com.forezp.serviceribbon.web;

import com.forezp.serviceribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: HelloController
 * @Description: TODO
 * @Author: ccg
 * @Date: 2019/6/20 15:13
 */

@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @RequestMapping("hello")
    public String hello(String name) {
        //调用Service接口，并返回JSON数据
        return helloService.obtainOtherServiceData(name);
    }

}
