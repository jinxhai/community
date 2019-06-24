package com.woniu.community.entity;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    private String  title;
    private String  description;
    private String  tag;
    private Long createTime;
    private Long modifiedTime;
    private Integer creatorId;
    private Integer viewCount;
    private Integer commentCount;
    private Integer loveCount;
    private User user;

}
