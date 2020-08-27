package com.fis.subscription;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.fis.subscription",
		"com.fis.subscription.controller",
		"com.fis.subscription.db"
})
public class RestSubscriptionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestSubscriptionServiceApplication.class, args);
	}

}
