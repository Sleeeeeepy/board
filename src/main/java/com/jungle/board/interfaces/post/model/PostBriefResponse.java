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
public class PostBriefResponse {
    private long authorId;
    private String title;
    private Date updatedAt;
    private Date createdAt;
    private String thumbnailUrl;

    //likes
    private long likes; 
    //혹시 로그인 되었는지 정보 api 하나만 부탁드려도 괜찮을까요?
    //api/v1/logincheck로 보내주시면 정말 감사드리겠습니다
}
