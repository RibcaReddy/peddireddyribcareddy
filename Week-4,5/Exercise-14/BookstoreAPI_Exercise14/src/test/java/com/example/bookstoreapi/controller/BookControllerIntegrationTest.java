package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl;

    @BeforeEach
    public void setup() {
        baseUrl = "http://localhost:" + port + "/books";
    }

    @Test
    public void testGetAllBooks() {
        ResponseEntity<Book[]> response = restTemplate.getForEntity(baseUrl, Book[].class);
        List<Book> books = Arrays.asList(response.getBody());
        assertThat(books).isEmpty();
    }

    @Test
    public void testAddBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book One");
        book.setAuthor("Author One");
        book.setPrice(29.99);
        book.setIsbn("1234567890");

        ResponseEntity<Book> response = restTemplate.postForEntity(baseUrl, book, Book.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);

        ResponseEntity<Book[]> getAllResponse = restTemplate.getForEntity(baseUrl, Book[].class);
        List<Book> books = Arrays.asList(getAllResponse.getBody());
        assertThat(books).hasSize(1);
    }

    @Test
    public void testUpdateBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book One Updated");
        book.setAuthor("Author One");
        book.setPrice(39.99);
        book.setIsbn("1234567890");

        HttpEntity<Book> requestEntity = new HttpEntity<>(book);
        ResponseEntity<Book> response = restTemplate.exchange(baseUrl + "/1", HttpMethod.PUT, requestEntity, Book.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);

        ResponseEntity<Book[]> getAllResponse = restTemplate.getForEntity(baseUrl, Book[].class);
        List<Book> books = Arrays.asList(getAllResponse.getBody());
        assertThat(books.get(0).getTitle()).isEqualTo("Book One Updated");
    }

    @Test
    public void testDeleteBook() {
        restTemplate.delete(baseUrl + "/1");

        ResponseEntity<Book[]> getAllResponse = restTemplate.getForEntity(baseUrl, Book[].class);
        List<Book> books = Arrays.asList(getAllResponse.getBody());
        assertThat(books).isEmpty();
    }
}
