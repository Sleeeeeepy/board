package com.jungle.board.interfaces.post.model;

import java.util.Date;

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
    private long authorId;
    private String title;
    private String body;
    private Date updatedAt;
    private Date createdAt;
    private String thumbnailUrl; 
    private long likes; 
}
