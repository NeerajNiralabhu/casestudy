package com.fis.subscription.controller;


import java.util.List;

import javax.websocket.server.PathParam;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.fis.subscription.db.SubscriptionDbUtil;
import com.fis.subscription.model.Book;
import com.fis.subscription.model.Subscription;
import com.fis.subscription.services.SubscriptionService;



@RestController
@RequestMapping("/subscriptions")
//@EnableFeignClients("com.fis.subscription.services")
public class SubscriptionRestController {
	@Autowired
	private SubscriptionDbUtil subscriptionDbUtil;
	
	@Autowired
	SubscriptionService subscriptionService;
	

	@RequestMapping(method = RequestMethod.GET)
	public List<Subscription> getSubscriptions() {
		try {
			return subscriptionDbUtil.getSubscriptions();
		} catch (NumberFormatException e) {
			throw new RuntimeException(e);
		}
	}

	@RequestMapping(value="/{name}",method = RequestMethod.GET)
	public Subscription getSubscriptionById(@PathVariable("name") String name) {
		try {
			return subscriptionDbUtil.getByName(name);
		} catch (NumberFormatException e) {
			throw new RuntimeException(e);
		}
	}
	@RequestMapping(consumes = "application/json",method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> saveEmployee(@RequestBody Subscription subscription) {
		return ResponseEntity.created(null).body(subscriptionService.createSubscription(subscription));
	}
	public SubscriptionDbUtil getEmployeeDbUtil() {
		return subscriptionDbUtil;
	}

	public void setEmployeeDbUtil(SubscriptionDbUtil subscriptionDbUtil) {
		this.subscriptionDbUtil = subscriptionDbUtil;
	}

}

