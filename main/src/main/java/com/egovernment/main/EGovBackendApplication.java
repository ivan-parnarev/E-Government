package com.egovernment.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@EnableCaching
@ComponentScan(basePackages = {"com.egovernment.main", "com.egovernment.kafka"})
public class EGovBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(EGovBackendApplication.class, args);
	}

}
