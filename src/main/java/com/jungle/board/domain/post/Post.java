package com.jungle.board.domain.post;

import java.sql.Date;

import org.hibernate.annotations.ColumnDefault;

import com.jungle.board.domain.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Entity(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @Column(name  = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "updatedAt", nullable = false)
    private Date updatedAt;
    
    @Column(name = "createdAt", nullable = false)
    private Date createdAt;

    @Column(name = "thumbnailUrl", nullable = true)
    private String thumbnailUrl;
    
    @Column(name = "deleted", nullable = false)
    @ColumnDefault("false")
    private Boolean deleted;

    public Post create() {
        var currentTime = new java.util.Date().getTime();
        return Post.builder()
                   .title("")
                   .content("")
                   .createdAt(new Date(currentTime))
                   .updatedAt(new Date(currentTime))
                   .thumbnailUrl("")
                   .deleted(false)
                   .build();                   
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setThumbnailUrl(String url) {
        this.thumbnailUrl = url;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        var currentDate = new java.util.Date();
        this.updatedAt = new Date(currentDate.getTime());
    }

    public void delete() {
        this.deleted = true;
    }
}