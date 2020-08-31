package com.fis.subscription.services;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.fis.subscription.model.Book;

//@FeignClient(name="rest-book-service",url="http://localhost:8081") //using hardcoded url to connect to particular exchange service
@FeignClient(name="rest-book-service") // removed hardcoded url to use ribbon
@RibbonClient(name="rest-book-service")
public interface BookServiceProxy {
     @GetMapping("/books")
     public List<Book> getBookById();
}
