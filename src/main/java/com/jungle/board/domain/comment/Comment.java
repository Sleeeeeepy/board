package com.jungle.board.domain.comment;

import java.sql.Date;

import org.hibernate.annotations.ColumnDefault;

import com.jungle.board.domain.post.Post;
import com.jungle.board.domain.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "updatedAt", nullable = false)
    private Date updatedAt;

    @Column(name = "createdAt", nullable = false)
    private Date createdAt;

    @Column(name = "deleted", nullable = false)
    @ColumnDefault("false")
    private boolean deleted;

    public void setAuthor(User author) {
        this.author = author;
    }

    public void update(String content) {
        this.content = content;
        var currentDate = new java.util.Date();
        this.updatedAt = new Date(currentDate.getTime());
    }

    public static Comment create(String content, User user, Post post) {
        Comment comment = new Comment();
        var now = new java.util.Date().getTime();

        comment.content = content;
        comment.post = post;
        comment.author = user;
        comment.createdAt = new Date(now);
        comment.updatedAt = new Date(now);
        return comment;
    }

    public void delete() {
        this.deleted = true;
    }
}