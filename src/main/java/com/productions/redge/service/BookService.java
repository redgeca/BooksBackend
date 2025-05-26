package com.productions.redge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.productions.redge.entity.BookEntity;
import com.productions.redge.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }
    
    public Page<BookEntity> getAllBooks(Pageable bookPage) {
    	return bookRepository.findAll(bookPage);
    	
    }
    
    public Optional<BookEntity> getBook(Long id) {
    	return bookRepository.findById(id);
    }

    public BookEntity addBook(BookEntity book) {
        return bookRepository.save(book);
    }

    public BookEntity updateBook(Long id, BookEntity book) {
        if (bookRepository.existsById(id)) {
            book.setId(id);
            return bookRepository.save(book);
        } else {
            throw new RuntimeException("Book not found");
        }
    }
    
    public void deleteBook(Long id) {
    	bookRepository.deleteById(id);
    }
    
    public void deleteBooks(List<Long> ids) {
    	bookRepository.deleteAllById(ids);
    }
    
    
}
