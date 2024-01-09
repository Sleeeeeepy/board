package com.jungle.board.application;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jungle.board.common.WebException;
import com.jungle.board.domain.comment.Comment;
import com.jungle.board.domain.comment.CommentRepository;
import com.jungle.board.domain.post.PostRepository;
import com.jungle.board.domain.user.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }


    @Override
    public Comment writeComment(Long userId, Long postId, String content) {
        var post = postRepository.findById(postId).orElseThrow(() -> {
            throw new WebException(HttpStatus.NOT_FOUND, "Post " + postId + " does not exists");
        });

        var user = userRepository.findById(userId).orElseThrow(() -> {
            throw new WebException(HttpStatus.NOT_FOUND, "User " + userId + " does not exists");
        });

        var comment = user.publishComment(post, content);
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Long commentId, String content) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteComment(Long commentId) {
        var comment = commentRepository.findById(commentId).orElseThrow(() -> {
            throw new WebException(HttpStatus.NOT_FOUND, "Comment " + commentId + " does not exists");
        });

        comment.delete();
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAll(Long postId) {
        var post = postRepository.findById(postId).orElseThrow(() -> {
            throw new WebException(HttpStatus.NOT_FOUND, "Post " + postId + " does not exists");
        });

        return post.getComments();
    }
}