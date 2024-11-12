package com.lyj.securitydomo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {
    private Long requestId;
    private Long postId;
    private Long userId;
    private String title;
    private String content;
    private String username;
    private String contentText;
    private Date regDate;
    private Long requestStatusId;
    private String status;

//    public RequestDTO(Long requestId, String content, String username, String contentText, Date regDate,Long postId, Long requestStatusId, Long userId,String title) {
//        this.requestId = requestId;
//        this.postId = postId;
//        this.userId = userId;
//        this.title = title;
//        this.content = content;
//        this.username = username;
//        this.contentText = contentText;
//        this.regDate = regDate;
//        this.requestStatusId = requestStatusId;
//
//    }

}


