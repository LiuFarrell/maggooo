package com.louis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MaggoooZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(MaggoooZuulApplication.class, args);
    }
}
