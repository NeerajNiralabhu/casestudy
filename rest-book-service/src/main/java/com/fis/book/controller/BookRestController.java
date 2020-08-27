package com.fis.book.controller;


import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fis.book.db.BookDbUtil;
import com.fis.book.model.Book;



@RestController
@RequestMapping("/books")
public class BookRestController {
	@Autowired
	private BookDbUtil bookDbUtil;
	
//	@RequestMapping(value="/{id}",method = RequestMethod.GET)
//	public Book getBookById(@PathVariable("id") String id) {
	@RequestMapping(method = RequestMethod.GET)
	public List<Book> getBookById() {
		try {
//			return bookDbUtil.getById(id);
			return bookDbUtil.getBooks();
		} catch (NumberFormatException e) {
			throw new RuntimeException(e);
		}
	}

	public BookDbUtil getEmployeeDbUtil() {
		return bookDbUtil;
	}

	public void setEmployeeDbUtil(BookDbUtil bookDbUtil) {
		this.bookDbUtil = bookDbUtil;
	}

}

