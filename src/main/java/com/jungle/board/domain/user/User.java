package com.jungle.board.domain.user;

import java.sql.Date;
import java.util.List;

import com.jungle.board.domain.comment.Comment;
import com.jungle.board.domain.post.Post;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
@Table(name = "user", indexes = @Index(name = "idx_nickname", columnList = "nickname"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nickname", unique = true, nullable = false)
    private String nickname;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "joined_at", nullable = false)
    private Date joinedAt;
    
    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "salt")
    private String salt;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Post> posts;

    @Embedded
    private RoomPosition roomPosition;

    public void publishPost(Post post) {
        posts.add(post);
        post.setAuthor(this);
    }

    public Comment publishComment(Post post, String content) {
        return post.writeComment(content, this);
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeRoomPosition(double x, double y, double z) {
        this.roomPosition = new RoomPosition(x, y, z);
    }
    
    public void setSalt(String salt) {
        this.salt = salt;
    }
}