package com.fis.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {
		"com.fis.book.db",
		"com.fis.book.controller",
		"com.fis.book"
})
@EnableDiscoveryClient
public class RestBookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestBookServiceApplication.class, args);
	}

}
