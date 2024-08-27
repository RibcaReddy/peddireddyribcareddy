package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookController bookController;

    private List<Book> books;

    @BeforeEach
    public void setup() {
        books = new ArrayList<>();
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Book One");
        book1.setAuthor("Author One");
        book1.setPrice(29.99);
        book1.setIsbn("1234567890");

        books.add(book1);

        when(bookController.getAllBooks()).thenReturn(books);
    }

    @Test
    public void testGetAllBooks() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Book One"));
    }

    @Test
    public void testAddBook() throws Exception {
        Book book = new Book();
        book.setId(2L);
        book.setTitle("Book Two");
        book.setAuthor("Author Two");
        book.setPrice(39.99);
        book.setIsbn("0987654321");

        when(bookController.addBook(book)).thenReturn(book);

        mockMvc.perform(post("/books")
                .contentType("application/json")
                .content("{"id": 2, "title": "Book Two", "author": "Author Two", "price": 39.99, "isbn": "0987654321"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Book Two"));
    }
}
