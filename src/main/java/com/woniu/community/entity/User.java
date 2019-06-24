package com.woniu.community.entity;

import lombok.Data;

/**
 * @ClassName User
 * @Author JinXueHai
 * @Description
 **/
@Data
public class User {
    private Integer userId;
    private String accountId;
    private String userName;
    private String token;
    private Long createTime;
    private Long modifiedTime;
    private String imgUrl;

}
