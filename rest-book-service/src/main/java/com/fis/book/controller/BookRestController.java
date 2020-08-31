package com.fis.book.controller;


import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fis.book.db.BookDbUtil;
import com.fis.book.model.Book;
import com.fis.book.services.BookServices;



@RestController
@RequestMapping("/books")
public class BookRestController {
	@Autowired
	private BookDbUtil bookDbUtil;
	
	@Autowired
	private BookServices bookService;
	
//	@RequestMapping(value="/{id}",method = RequestMethod.GET)
//	public Book getBookById(@PathVariable("id") String id) {
	@RequestMapping(method = RequestMethod.GET)
	public List<Book> getBookById() {
		try {
//			return bookDbUtil.getById(id);
			return bookService.retrieveBooks();

		} catch (NumberFormatException e) {
			throw new RuntimeException(e);
		}
	}

	  @KafkaListener(topics = {"spring-kafka-book-subscription-group"})
	    public void consumeMessage(String bookIdToBeUpdated) {
	        
	    	bookService.decrAvailableCount(bookIdToBeUpdated);
	    }
	  

}

