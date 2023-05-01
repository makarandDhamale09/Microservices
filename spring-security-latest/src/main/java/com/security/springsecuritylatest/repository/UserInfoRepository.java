package com.security.springsecuritylatest.repository;

import com.security.springsecuritylatest.entity.UserInfo;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserInfoRepository extends MongoRepository<UserInfo, Integer> {
  Optional<UserInfo> findByUserName(String username);
}
