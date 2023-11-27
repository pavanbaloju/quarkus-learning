package com.example.quarkus.learning.repository;

import com.example.quarkus.learning.model.Book;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BookRepository {
    private final List<Book> books;

    public BookRepository() {
        books = new ArrayList<>();
        books.add(new Book(1, "The Catcher in the Rye", "J.D. Salinger", 1951, "Fiction"));
        books.add(new Book(2, "Introduction to Programming", "John Doe", 2020, "Programming"));
        books.add(new Book(3, "To Kill a Mockingbird", "Harper Lee", 1960, "Fiction"));
        books.add(new Book(4, "1984", "George Orwell", 1949, "Dystopian Fiction"));
        books.add(new Book(5, "The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979, "Science"));
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBookById(int id) {
        return books.stream().filter(book -> book.getId() == id).findFirst().orElse(null);
    }
}
