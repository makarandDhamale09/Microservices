package com.myproj.springtest.controller;

import com.myproj.springtest.model.dto.CommentDto;
import com.myproj.springtest.model.dto.NewCommentDto;
import com.myproj.springtest.model.dto.PostDto;
import com.myproj.springtest.service.CommentService;
import com.myproj.springtest.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long id){
        Optional<PostDto> post = Optional.ofNullable(postService.getPost(id));
        return post.map(p->new ResponseEntity<>(p, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<PostDto> addPost(@RequestBody PostDto postDto){
        PostDto savedPost = postService.addPost(postDto);
        return new ResponseEntity<>(savedPost,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(){
        List<PostDto> allPosts = postService.getAllPosts();
        return ResponseEntity.ok(allPosts);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<String> addNewComment(@PathVariable Long id, @RequestBody NewCommentDto newComment){
        String message = "";
        Long postId  = id;
        try {
            postId = commentService.addComment(id, newComment);
        }catch (IllegalArgumentException ex){
            message = ex.getMessage();
            return new ResponseEntity<>("PostId : " + message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("PostId : " + postId, HttpStatus.OK);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable Long id){
        List<CommentDto> comments = commentService.getCommentsForPost(id);
        return ResponseEntity.ok(comments);
    }
}
