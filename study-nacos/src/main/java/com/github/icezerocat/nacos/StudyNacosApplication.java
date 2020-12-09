package com.github.icezerocat.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 0.0.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class StudyNacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyNacosApplication.class, args);
    }

}
