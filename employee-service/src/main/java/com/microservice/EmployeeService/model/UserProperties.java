package com.microservice.EmployeeService.model;

import java.util.List;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "user-properties")
public class UserProperties {
  private List<String> files;
  private Map<String, UserDetails> userDetails;

  public List<String> getFiles() {
    return files;
  }

  public void setFiles(List<String> files) {
    this.files = files;
  }

  public Map<String, UserDetails> getUserDetails() {
    return userDetails;
  }

  public void setUserDetails(Map<String, UserDetails> userDetails) {
    this.userDetails = userDetails;
  }

  public static class UserDetails {
    private String userName;
    private String password;

    public String getUserName() {
      return userName;
    }

    public void setUserName(String userName) {
      this.userName = userName;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }
  }
}
