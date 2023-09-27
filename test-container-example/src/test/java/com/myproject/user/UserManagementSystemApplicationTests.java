package com.myproject.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myproject.user.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserManagementSystemApplicationTests {

  @Autowired private MockMvc mockMvc;

  @Test
  void contextLoads() {}

  public static String asJsonString(final Object obj) throws Exception {
    try {
      final ObjectMapper objectMapper = new ObjectMapper();
      final String jsonContent = objectMapper.writeValueAsString(obj);
      return jsonContent;
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
            MockMvcRequestBuilders.get("/api/vi/user/allUsers")
                .contentType("application/json")
                .accept("application/json"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    // .andExpect(MockMvcResultMatchers.jsonPath("$", ))
  }
}
