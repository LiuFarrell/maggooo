package com.louis.maggooo.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan(basePackages = "com.louis.maggooo.admin.dao")
@ComponentScan(basePackages = {"com.louis.maggooo.admin.*"})
@EnableDiscoveryClient
public class MaggoooAdminApplication
{
    public static void main(String[] args) {
        SpringApplication.run(MaggoooAdminApplication.class, args);
    }

}
