package com.fis.subscription.model;

public class Book {
	private String bookId;
	private String name;
	private String author;
	private Integer copiesAvailable ;
	private Integer totalCopies;
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getCopiesAvailable() {
		return copiesAvailable;
	}
	public void setCopiesAvailable(Integer copiesAvailable) {
		this.copiesAvailable = copiesAvailable;
	}
	public Integer getTotalCopies() {
		return totalCopies;
	}
	public void setTotalCopies(Integer totalCopies) {
		this.totalCopies = totalCopies;
	}



}
