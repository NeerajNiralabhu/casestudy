package com.fis.book.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fis.book.db.BookDbUtil;
import com.fis.book.model.Book;

@Service
public class BookServices {


		@Autowired
		private BookDbUtil bookDbUtil;

		public List<Book> retrieveBooks() {

			List<Book> bookList = bookDbUtil.getBooks();
			return bookList;
		}
		
	    public void decrAvailableCount(String bookIdToBeUpdated) {

			Book bookToBeUpdated = bookDbUtil.getById(bookIdToBeUpdated);
			if (bookToBeUpdated == null || bookToBeUpdated.getCopiesAvailable() == 0) {

				throw new RuntimeException("Book with id: " + bookIdToBeUpdated + " not available!");
			} else {

				bookToBeUpdated.setCopiesAvailable(bookToBeUpdated.getCopiesAvailable() - 1);
				bookDbUtil.updateBook(bookToBeUpdated);
			}
	    }
		
	}


