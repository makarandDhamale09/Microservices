package com.security.springsecuritylatest.service;

import com.security.springsecuritylatest.config.UserInfoDetails;
import com.security.springsecuritylatest.entity.UserInfo;
import com.security.springsecuritylatest.repository.UserInfoRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserInfoDetailsService implements UserDetailsService {

  @Autowired private UserInfoRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UserInfo> userInfo = repository.findByUserName(username);

    return userInfo
        .map(UserInfoDetails::new)
        .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
  }
}
