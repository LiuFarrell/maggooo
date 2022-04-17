package com.louis;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 */
@EnableAdminServer
@EnableDiscoveryClient
@SpringBootApplication
public class MaggoooMonitorApplication
{
    public static void main(String[] args) {
        SpringApplication.run(MaggoooMonitorApplication.class, args);
    }
}
