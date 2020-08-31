package com.fis.subscription;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {
		"com.fis.subscription",
		"com.fis.subscription.controller",
		"com.fis.subscription.db"
})
@EnableFeignClients("com.fis.subscription.services")
@EnableDiscoveryClient
public class RestSubscriptionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestSubscriptionServiceApplication.class, args);
	}

}
