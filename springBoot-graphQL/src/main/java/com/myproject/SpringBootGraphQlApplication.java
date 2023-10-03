package com.myproject;

import com.myproject.model.Author;
import com.myproject.model.Book;
import com.myproject.repository.AuthorRepository;
import com.myproject.repository.BookRepository;
import java.util.List;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootGraphQlApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootGraphQlApplication.class, args);
  }

  @Bean
  public ApplicationRunner applicationRunner(
      AuthorRepository authorRepository, BookRepository bookRepository) {
    return args -> {
      Author josh = authorRepository.save(new Author("Josh"));
      Author mark = authorRepository.save(new Author("mark"));
      bookRepository.saveAll(
          List.of(
              new Book("Reactive Spring", "ABC Publisher", josh),
              new Book("Cloud Native Java", "DEF Publisher", josh),
              new Book("Spring boot Up & Running", "ABC Publisher", mark)));
    };
  }
}
