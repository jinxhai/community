package com.woniu.community.entity;


import lombok.Data;

/**
 * @ClassName AccessToken
 * @Author JinXueHai
 * @Description token参数类
 **/
@Data
public class AccessToken {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
