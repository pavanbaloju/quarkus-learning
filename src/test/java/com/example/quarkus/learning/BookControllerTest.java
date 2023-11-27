package com.example.quarkus.learning;

import com.example.quarkus.learning.model.Book;
import com.example.quarkus.learning.repository.BookRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
class BookControllerTest {

    @Inject
    BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        // Initialize RestAssured base URI
        RestAssured.baseURI = "http://localhost";
        // Initialize RestAssured port
        RestAssured.port = 8081; // Update the port based on your Quarkus application configuration
    }

    @Test
    void testGetAllBooks() {
        List<Book> expectedBooks = Arrays.asList(
            new Book(1, "The Catcher in the Rye", "J.D. Salinger", 1951, "Fiction"),
            new Book(2, "Introduction to Programming", "John Doe", 2020, "Programming"),
            new Book(3, "To Kill a Mockingbird", "Harper Lee", 1960, "Fiction"),
            new Book(4, "1984", "George Orwell", 1949, "Dystopian Fiction"),
            new Book(5, "The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979, "Science")
        );

        given()
            .when()
            .get("/api/books")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("$", hasSize(equalTo(expectedBooks.size())));
    }

    @Test
    void testGetBooksCount() {
        given()
            .when().get("/api/books/count")
            .then()
            .statusCode(200)
            .contentType(ContentType.TEXT)
            .body(equalTo("5"));
    }

    @Test
    void testGetBookById() {
        int bookId = 1;
        Book expectedBook = new Book(bookId, "The Catcher in the Rye", "J.D. Salinger", 1951, "Fiction");

        given()
            .pathParam("id", bookId)
            .when().get("/api/books/{id}")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("id", equalTo(expectedBook.getId()))
            .body("title", equalTo(expectedBook.getTitle()))
            .body("author", equalTo(expectedBook.getAuthor()))
            .body("yearOfPublication", equalTo(expectedBook.getYearOfPublication()))
            .body("genre", equalTo(expectedBook.getGenre()));
    }
}
