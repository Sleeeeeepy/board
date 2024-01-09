package com.jungle.board.interfaces.post.model;

import java.util.Date;

import com.jungle.board.domain.post.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PostResponse {
    private long postId;
    private long authorId;
    private String nickname;
    private String title;
    private String body;
    private Date updatedAt;
    private Date createdAt;
    private String thumbnailUrl; 
    private long likes;

    public static PostResponse fromPost(Post post) {
        return PostResponse.builder()
                    .postId(post.getId())
                    .authorId(post.getAuthor().getId())
                    .nickname(post.getAuthor().getNickname())
                    .title(post.getTitle())
                    .body(post.getContent())
                    .updatedAt(post.getUpdatedAt())
                    .createdAt(post.getCreatedAt())
                    .thumbnailUrl(post.getThumbnailUrl())
                    .build();
    }
}
