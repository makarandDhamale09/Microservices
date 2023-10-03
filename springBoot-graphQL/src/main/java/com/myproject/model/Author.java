package com.myproject.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Author {
  @Id @GeneratedValue Long id;
  private String name;

  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
  private List<Book> books = new ArrayList<>();

  public Author(String name) {
    this.name = name;
  }
}
