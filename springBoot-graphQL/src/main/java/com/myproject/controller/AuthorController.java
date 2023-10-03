package com.myproject.controller;

import com.myproject.model.Author;
import com.myproject.model.Book;
import com.myproject.repository.AuthorRepository;
import com.myproject.repository.BookRepository;
import java.util.Optional;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AuthorController {

  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;

  public AuthorController(AuthorRepository authorRepository, BookRepository bookRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
  }

  @QueryMapping
  public Iterable<Author> authors() {
    return authorRepository.findAll();
  }

  @QueryMapping
  public Optional<Author> authorById(@Argument Long id) {
    return authorRepository.findById(id);
  }

  @MutationMapping
  Book addBook(@Argument BookInput bookInput) {
    Author author =
        authorRepository
            .findById(bookInput.authorId)
            .orElseThrow(() -> new IllegalArgumentException("Author not found"));

    Book book = new Book(bookInput.title(), bookInput.publisher, author);
    return bookRepository.save(book);
  }

  record BookInput(String title, String publisher, Long authorId) {}
}
