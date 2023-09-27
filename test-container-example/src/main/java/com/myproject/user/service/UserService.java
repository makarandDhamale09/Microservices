package com.myproject.user.service;

import com.myproject.user.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
  User saveUser(User user);

  List<User> getAllUsers();

  Optional<User> getUserById(Long id);

  boolean deleteUserById(Long id);

  User updateUser(User user);
}
