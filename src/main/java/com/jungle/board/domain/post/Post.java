package com.jungle.board.domain.post;

import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import com.jungle.board.domain.comment.Comment;
import com.jungle.board.domain.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "content", columnDefinition = "text", nullable = false)
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

    @OneToMany(mappedBy="post", fetch = FetchType.LAZY)
    private List<Comment> comments;

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setThumbnailUrl(String url) {
        this.thumbnailUrl = url;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = PostUtils.filterContent(content);
        var currentDate = new java.util.Date();
        this.updatedAt = new Date(currentDate.getTime());
        this.thumbnailUrl = PostUtils.getThumbnailUrlFromContent(this.content);
    }

    public void delete() {
        this.deleted = true;
    }

    public Comment writeComment(String content, User user) {
        var comment = Comment.create(content, user, this);
        this.comments.add(comment);
        return comment;
    }
}