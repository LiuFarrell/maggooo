package com.louis.service;

import com.louis.service.impl.MaggoooProducerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "maggooo-producer",fallback = MaggoooProducerHystrix.class)
public interface MaggooProducerService {
    @RequestMapping(value = "/hello")
    public String hello();
}
