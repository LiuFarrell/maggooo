package com.louis.service.impl;

import com.louis.service.MaggooProducerService;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class MaggoooProducerHystrix implements MaggooProducerService {

    @Override
    @RequestMapping(name = "/hello")
    public String hello() {
        return "sorry,hello service call failed.";
    }
}
