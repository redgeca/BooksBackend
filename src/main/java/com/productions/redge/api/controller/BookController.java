package com.productions.redge.api.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productions.redge.entity.BookEntity;
import com.productions.redge.service.BookService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Pageable;



@RestController
@RequestMapping("/api/v1/books")
@CrossOrigin(origins = "*", exposedHeaders = "total-count")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    @GetMapping
    ResponseEntity<List<BookEntity>> getAllBooks(@RequestParam Map<String, String> requestParams) {
    	int page = requestParams.get("page") != null ? Integer.parseInt(requestParams.get("page")) : 0;
    	int perPage = requestParams.get("perPage") != null ? Integer.parseInt(requestParams.get("perPage")) : 10;
    	Pageable bookPage = PageRequest.of(page, perPage);
    	
        Page<BookEntity> books = bookService.getAllBooks(bookPage);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("total-count", String.valueOf(books.getTotalElements()));
        return ResponseEntity.ok().headers(headers).body(books.getContent());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BookEntity> getBook(@PathVariable Long id) {
    	return ResponseEntity.of(bookService.getBook(id));
    }

    @PostMapping
    public ResponseEntity<BookEntity> addBook(@RequestBody BookEntity book) {
        BookEntity savedBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookEntity> saveBook(@PathVariable Long id, @RequestBody BookEntity book) {
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    @DeleteMapping("/{ids}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> deleteBooks(@PathVariable List<Long> ids) {
    	
    	bookService.deleteBooks(ids);
    	return ResponseEntity.noContent().build();
    }
}
