package com.egovernment.accesscontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {"com.egovernment.accesscontrol", "com.egovernment.kafka"})
public class EGovernmentAccessControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(EGovernmentAccessControlApplication.class, args);
    }

}
