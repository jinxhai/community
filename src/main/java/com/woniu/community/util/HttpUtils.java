package com.woniu.community.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.woniu.community.entity.AccessToken;
import com.woniu.community.entity.GithubUser;
import okhttp3.*;

import java.io.IOException;

/**
 * @ClassName HttpUtils
 * @Author lenovo
 * @Description //TODO $
 * @Date $ $
 **/
public class HttpUtils {
    /**
     * @Author JinXueHai
     * @Description  post请求http链接
     * @params
     * @Date  2019/6/19 16:17
     **/
    public static String httpPost(AccessToken accessToken,String url){
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        String jsonStr = JSONObject.toJSONString(accessToken);
        RequestBody body = RequestBody.create(JSON,jsonStr);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            String[] split = string.split("&");
            String token = split[0].split("=")[1];
            System.out.println(string);
            System.err.println(token);
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public static GithubUser httpGet(String url){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                    .url(url)
                    .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return  githubUser;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}
