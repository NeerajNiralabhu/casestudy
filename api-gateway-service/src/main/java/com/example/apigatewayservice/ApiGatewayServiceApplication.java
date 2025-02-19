package com.example.apigatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ApiGatewayServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayServiceApplication.class, args);
	}

}
