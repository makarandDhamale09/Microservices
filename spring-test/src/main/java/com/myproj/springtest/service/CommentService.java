package com.myproj.springtest.service;

import com.myproj.springtest.model.Comment;
import com.myproj.springtest.model.Post;
import com.myproj.springtest.model.dto.CommentDto;
import com.myproj.springtest.model.dto.NewCommentDto;
import com.myproj.springtest.repository.CommentRepository;
import com.myproj.springtest.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    /**
     * Returns a list of all comments for a blog post with passed id.
     *
     * @param postId id of the post
     * @return list of comments sorted by creation date descending - most recent first
     */
    public List<CommentDto> getCommentsForPost(Long postId) {
        return commentRepository.findCommentsByPostId(postId)
                .stream().map(comment -> new CommentDto(comment.getCommentId(),
                        comment.getComment(), comment.getAuthor(),
                        comment.getCreationDate()))
                .collect(Collectors.toList());
    }

    /**
     * Creates a new comment
     *
     * @param postId id of the post
     * @param newCommentDto data of new comment
     * @return id of the created comment
     *
     * @throws IllegalArgumentException if postId is null or there is no blog post for passed postId
     */
    public Long addComment(Long postId, NewCommentDto newCommentDto) throws IllegalArgumentException {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("Post Id cannot be null"));
        Comment comment = new Comment();
        comment.setComment(newCommentDto.content());
        comment.setAuthor(newCommentDto.author());
        comment.setCreationDate(LocalDateTime.now());
        post.getComments().add(comment);
        Post savedPost = postRepository.save(post);
        return savedPost.getId();
    }
}
