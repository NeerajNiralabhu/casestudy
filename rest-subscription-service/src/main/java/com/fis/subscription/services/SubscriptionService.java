package com.fis.subscription.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fis.subscription.db.SubscriptionDbUtil;
import com.fis.subscription.model.Book;
import com.fis.subscription.model.Subscription;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class SubscriptionService {

	@Autowired
	private SubscriptionDbUtil subscriptionDbUtil;

	@Autowired
	BookServiceProxy bookServiceProxy;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@HystrixCommand(fallbackMethod = "fallbackForBookRetrieval")
	public HttpStatus createSubscription(Subscription subscription) {

		// Using RestTemplate
		/*
		 * ResponseEntity<Book[]> response = new RestTemplate()
		 * .getForEntity("http://localhost:8081/books/",Book[].class); Book[] books =
		 * response.getBody();
		 */
		Book bookToBeUpdated = null;

		List<Book> books = bookServiceProxy.getBookById();

		for (Book book : books) {
			if (book.getBookId().equals(subscription.getBookId())) {
				bookToBeUpdated = book;
				break;
			}
		}

		if (bookToBeUpdated == null || bookToBeUpdated.getCopiesAvailable() == 0) {
			return HttpStatus.UNPROCESSABLE_ENTITY;
		}

		bookToBeUpdated.setCopiesAvailable(bookToBeUpdated.getCopiesAvailable() - 1);
		try {
			subscriptionDbUtil.insertData(subscription);

//			updateSubscription(subscription);
		} catch (Exception e) {

			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		kafkaTemplate.send("spring-kafka-book-subscription-group", bookToBeUpdated.getBookId());

		return HttpStatus.CREATED;
	}

	public HttpStatus fallbackForBookRetrieval(Subscription subscription) {

		return HttpStatus.INTERNAL_SERVER_ERROR;
	}

}
