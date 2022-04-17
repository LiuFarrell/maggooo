package com.louis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ServiceController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/services")
    public Object service(){
        return discoveryClient.getInstances("maggooo-producer");
    }

    @RequestMapping(value = "/discover")
    public Object discover(){
        return loadBalancerClient.choose("maggooo-producer").getUri().toString();
    }

    @RequestMapping(value = "/call")
    public Object call(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("maggooo-producer");
        System.out.println("服务器地址："+serviceInstance.getUri());
        System.out.println("服务名称："+serviceInstance.getServiceId());
        String forObject = new RestTemplate().getForObject(serviceInstance.getUri().toString() + "/hello", String.class);
        System.out.println(forObject);
        return forObject;
    }

}
