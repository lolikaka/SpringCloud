package com.forezp.sericefeign.web;

import com.forezp.sericefeign.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: HelloController
 * @Description: TODO
 * @Author: ccg
 * @Date: 2019/6/20 16:49
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/sayHello")
    public String sayHello(String name) {
        return helloService.obtainOtherServerJsonData(name);
    }

}
