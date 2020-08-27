package com.fis.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.fis.book.db",
		"com.fis.book.controller",
		"com.fis.book"
})
public class RestBookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestBookServiceApplication.class, args);
	}

}
