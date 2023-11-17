package com.myproj.springtest.service;

import com.myproj.springtest.model.Post;
import com.myproj.springtest.model.dto.PostDto;
import com.myproj.springtest.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto getPost(Long id) {
        return postRepository.findById(id)
                .map(post -> new PostDto(post.getId(), post.getTitle(), post.getContent(), post.getCreationDate()))
                .orElse(null);
    }

    public PostDto addPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.title());
        post.setContent(postDto.content());
        post.setCreationDate(LocalDateTime.now());
        Post savedPost = postRepository.save(post);
        return new PostDto(savedPost.getId(),
                savedPost.getTitle(), savedPost.getContent(),
                savedPost.getCreationDate());
    }

    public List<PostDto> getAllPosts() {
        return postRepository.findAll()
                .stream().map(post -> new PostDto(post.getId(),
                        post.getTitle(), post.getContent(),
                        post.getCreationDate()))
                .collect(Collectors.toList());
    }
}
