package com.myproject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Book {
  @Id @GeneratedValue private Long id;

  private String title;

  private String publisher;

  @ManyToOne(fetch = FetchType.LAZY)
  private Author author;

  public Book(String title, String publisher, Author author) {
    this.title = title;
    this.publisher = publisher;
    this.author = author;
  }
}
