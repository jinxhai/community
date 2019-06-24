package com.woniu.community.entity;

import lombok.Data;

/**
 * @ClassName GithubUser
 * @Author JinXueHai
 * @Description
 **/
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;

}
