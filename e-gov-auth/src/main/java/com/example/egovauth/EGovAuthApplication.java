package com.example.egovauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class EGovAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(EGovAuthApplication.class, args);
	}

}
