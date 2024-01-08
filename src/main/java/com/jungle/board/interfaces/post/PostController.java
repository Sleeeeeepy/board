package com.jungle.board.interfaces.post;

import java.sql.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jungle.board.application.AuthService;
import com.jungle.board.application.PostService;
import com.jungle.board.domain.post.Post;
import com.jungle.board.interfaces.post.model.PostDeleteRequest;
import com.jungle.board.interfaces.post.model.PostRequest;
import com.jungle.board.interfaces.post.model.PostResponse;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    private final AuthService authService;

    public PostController(PostService postService, AuthService authService) {
        this.postService = postService;
        this.authService = authService;
    }

    @GetMapping("/{postId}")
    @ResponseBody
    public ResponseEntity<PostResponse> getPost(@PathVariable Long postId) {
        var post = postService.getPost(postId);
        var response = PostResponse.builder()
                                   .authorId(post.getAuthor().getId())
                                   .title(post.getTitle())
                                   .body(post.getContent())
                                   .updatedAt(post.getUpdatedAt())
                                   .createdAt(post.getCreatedAt())
                                   .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<PostResponse> publishPost(@RequestBody PostRequest request) {
        var now = new java.util.Date().getTime();
        var post = Post.builder()
                       .title(request.getTitle())
                       .content(request.getContent())
                       .createdAt(new Date(now))
                       .updatedAt(new Date(now))
                       .deleted(false)
                       .build();
                       
        post = postService.writePost(request.getUserId(), post);
        var response = PostResponse.builder()
                                   .authorId(post.getAuthor().getId())
                                   .title(post.getTitle())
                                   .body(post.getContent())
                                   .updatedAt(post.getUpdatedAt())
                                   .createdAt(post.getCreatedAt())
                                   .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResponseEntity<PostResponse> deletePost(@RequestBody PostDeleteRequest request) {
        var post = postService.getPost(request.getPostId());
        postService.deletePost(request.getUserId(), post);
        var response = PostResponse.builder()
                           .authorId(post.getAuthor().getId())
                           .title(post.getTitle())
                           .body(post.getContent())
                           .updatedAt(post.getUpdatedAt())
                           .createdAt(post.getCreatedAt())
                           .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}