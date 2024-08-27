package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.mapper.Mapper;
import com.example.bookstoreapi.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return books.stream()
                .map(Mapper::toBookDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        Book book = books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElse(null);

        return Mapper.toBookDTO(book);
    }

    @PostMapping
    public BookDTO addBook(@RequestBody BookDTO bookDTO) {
        Book book = Mapper.toBookEntity(bookDTO);
        books.add(book);
        return Mapper.toBookDTO(book);
    }

    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable Long id, @RequestBody BookDTO bookDetails) {
        Book book = books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (book != null) {
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setPrice(bookDetails.getPrice());
            book.setIsbn(bookDetails.getIsbn());
        }

        return Mapper.toBookDTO(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }
}
