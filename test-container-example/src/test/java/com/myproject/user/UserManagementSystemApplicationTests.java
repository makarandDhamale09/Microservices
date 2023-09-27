package com.myproject.user;

import com.myproject.user.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
class UserManagementSystemApplicationTests {

  @Autowired private MockMvc mockMvc;

  @Container @ServiceConnection // After springBoot 3.1.0
  private static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.1.0");

  // Before SpringBoot 3.1.0
  //  @DynamicPropertySource
  //  static void dynamicConfiguration(DynamicPropertyRegistry registry){
  //    registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
  //    registry.add("spring.datasource.username", mySQLContainer::getUsername);
  //    registry.add("spring.datasource.password", mySQLContainer::getPassword);
  //  }

  @Test
  void contextLoads() {}

  public static String asJsonString(final Object obj) throws Exception {
    try {
      final ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      throw new Exception("Unable to process the user Object");
    }
  }

  @Test
  void testSaveUser() throws Exception {
    UserEntity user = new UserEntity();
    user.setEmailId("abc@gmail.com");
    user.setFirstName("John");
    user.setLastName("Doe");

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/v1/user")
                .contentType("application/json")
                .content(asJsonString(user))
                .accept("application/json"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
  }

  @Test
  void testGetAllEmployees() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/api/v1/user/allUsers")
                .contentType("application/json")
                .accept("application/json"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1));
  }
}
