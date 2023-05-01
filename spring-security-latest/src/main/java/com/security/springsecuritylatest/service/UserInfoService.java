package com.security.springsecuritylatest.service;

import com.security.springsecuritylatest.entity.UserInfo;
import com.security.springsecuritylatest.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

  @Autowired private UserInfoRepository userInfoRepository;

  @Autowired private PasswordEncoder passwordEncoder;

  public String addUser(UserInfo userInfo) {
    userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
    userInfoRepository.save(userInfo);
    return "User added to service";
  }
}
