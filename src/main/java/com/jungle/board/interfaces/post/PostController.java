package com.jungle.board.interfaces.post;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jungle.board.application.AuthService;
import com.jungle.board.application.PostService;
import com.jungle.board.interfaces.common.model.SimpleResponse;
import com.jungle.board.interfaces.post.model.PostDeleteRequest;
import com.jungle.board.interfaces.post.model.PostRequest;
import com.jungle.board.interfaces.post.model.PostResponse;
import com.jungle.board.interfaces.post.model.PostUpdateRequest;

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
        var response = PostResponse.fromPost(post);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<PostResponse> publishPost(@RequestBody PostRequest request) {
        authService.verifyUser(request.getUserId(), request.getJwt());
        var post = postService.writePost(request.getUserId(), request.getTitle(), request.getContent());
        var response = PostResponse.fromPost(post);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/delete/")
    @ResponseBody
    public ResponseEntity<SimpleResponse> deletePost(@RequestBody PostDeleteRequest request) {
        authService.verifyUser(request.getUserId(), request.getJwt());
        postService.deletePost(request.getPostId());
        var response = new SimpleResponse("Delete request is processed successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/")
    public ResponseEntity<PostResponse> updatePost(@RequestBody PostUpdateRequest request) {
        authService.verifyUser(request.getUserId(), request.getJwt());
        var post = postService.updatePost(request.getPostId(), request.getTitle(), request.getContent());
        var response = PostResponse.fromPost(post);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all/{nickname}")
    public ResponseEntity<List<PostResponse>> getAllPosts(@PathVariable String nickname) {
        var posts = postService.getAll(nickname);
        var response = posts.stream()
                            .filter((post) -> !post.getDeleted())
                            .map(post -> PostResponse.fromPost(post))
                            .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}