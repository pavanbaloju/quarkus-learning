package com.example.quarkus.learning.controller;

import com.example.quarkus.learning.model.Book;
import com.example.quarkus.learning.repository.BookRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/api/books")
public class BookController {

    private final BookRepository bookRepository;
    private final Logger logger;

    @Inject
    public BookController(BookRepository bookRepository, Logger logger) {
        this.bookRepository = bookRepository;
        this.logger = logger;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAllBooks() {
        logger.info("returning all books");
        return bookRepository.getAllBooks();
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public int getBooksCount() {
        logger.info("returning books count");
        return bookRepository.getAllBooks().size();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBookById(@PathParam("id") int id) {
        logger.info("returning book by id:" + id);
        return bookRepository.getBookById(id);
    }
}
