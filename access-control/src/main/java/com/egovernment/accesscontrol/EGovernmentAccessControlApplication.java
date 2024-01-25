package com.egovernment.accesscontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EGovernmentAccessControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(EGovernmentAccessControlApplication.class, args);
    }

}
