package com.myproject.batch;

import com.myproject.model.User;
import com.myproject.repository.UserRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class DBWriter implements ItemWriter<User> {

  private final UserRepository userRepository;

  public DBWriter(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void write(Chunk<? extends User> chunk) throws Exception {
    System.out.println("Items saved for users " + chunk.size());
    chunk.getItems().forEach(userRepository::save);
  }
}
