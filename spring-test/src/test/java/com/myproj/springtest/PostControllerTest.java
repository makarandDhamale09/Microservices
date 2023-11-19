package com.myproj.springtest;

import com.myproj.springtest.model.dto.PostDto;
import com.myproj.springtest.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostService postService;

    @Test
    public void testYourEndpoint() throws Exception {
        //given
        List<PostDto> postDtos = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 10, 10, 10);
        PostDto postDto1 = new PostDto(1L, "Post1", "Post 1", localDateTime);
        postDtos.add(postDto1);

        //when
        //when(postService.getAllPosts()).thenReturn(postDtos);

        postService.addPost(postDto1);

        mockMvc.perform(get("/posts/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Post1"));
    }
}
