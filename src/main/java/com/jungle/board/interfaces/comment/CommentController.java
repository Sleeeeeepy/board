package com.jungle.board.interfaces.comment;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jungle.board.application.AuthService;
import com.jungle.board.application.CommentService;
import com.jungle.board.interfaces.comment.model.CommentDeleteRequest;
import com.jungle.board.interfaces.comment.model.CommentRequest;
import com.jungle.board.interfaces.comment.model.CommentResponse;
import com.jungle.board.interfaces.common.model.SimpleResponse;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private final AuthService authService;

    public CommentController(CommentService commentService, AuthService authService) {
        this.commentService = commentService;
        this.authService = authService;
    }

    @PostMapping("/")
    public ResponseEntity<CommentResponse> publishComment(@RequestBody CommentRequest request) {
        var comment = commentService.writeComment(request.getUserId(), request.getPostId(), request.getContent());
        return new ResponseEntity<>(CommentResponse.fromComment(comment), HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Long postId) {
        var comments = commentService.getAll(postId);
        var response = comments.stream()
                               .filter(comment -> !comment.isDeleted())
                               .map(comment -> CommentResponse.fromComment(comment))
                               .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/delete/")
    public ResponseEntity<SimpleResponse> deleteComment(@RequestBody CommentDeleteRequest request) {
        commentService.deleteComment(request.getCommentId());
        var response = new SimpleResponse("Delete request is processed successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}