package com.louis.controller;

import com.louis.service.MaggooProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignServiceController {
    @Autowired
    private MaggooProducerService maggooProducerService;

    @RequestMapping(value = "/feign/call")
    public String call(){
        return maggooProducerService.hello();
    }
}
