package com.fis.subscription.controller;


import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fis.subscription.db.SubscriptionDbUtil;
import com.fis.subscription.model.Subscription;



@RestController
@RequestMapping("/subscriptions")
public class SubscriptionRestController {
	@Autowired
	private SubscriptionDbUtil subscriptionDbUtil;
	
//	@RequestMapping(value="/{id}",method = RequestMethod.GET)
//	public Book getBookById(@PathVariable("id") String id) {
	@RequestMapping(method = RequestMethod.GET)
	public List<Subscription> getSubscriptions() {
		try {
//			return bookDbUtil.getById(id);
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
	public String saveEmployee(@RequestBody Subscription subscription) {
		subscriptionDbUtil.insertData(subscription);
		return "SUCCESS";
	}
	public SubscriptionDbUtil getEmployeeDbUtil() {
		return subscriptionDbUtil;
	}

	public void setEmployeeDbUtil(SubscriptionDbUtil subscriptionDbUtil) {
		this.subscriptionDbUtil = subscriptionDbUtil;
	}

}

