package com.louis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MaggoooProducerApplication
{
    public static void main(String[] args) {
        SpringApplication.run(MaggoooProducerApplication.class, args);
    }
}
