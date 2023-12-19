package com.egovernment.egovbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EGovBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EGovBackendApplication.class, args);
	}

}
