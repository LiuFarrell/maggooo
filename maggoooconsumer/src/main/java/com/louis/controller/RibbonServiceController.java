package com.louis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonServiceController {
/*    @Autowired
    private LoadBalancerClient loadBalancerClient;*/
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/ribbon/call")
    public Object call(){
/*        ServiceInstance serviceInstance = loadBalancerClient.choose("maggooo-producer");
        System.out.println("服务器地址："+serviceInstance.getUri());
        System.out.println("服务名称："+serviceInstance.getServiceId());*/
        String forObject = restTemplate.getForObject("http://maggooo-producer/hello", String.class);
        System.out.println(forObject);
        return forObject;
    }

}
