package com.woniu.community.controller;

import com.woniu.community.entity.AccessToken;
import com.woniu.community.entity.GithubUser;
import com.woniu.community.util.HttpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthorizeController {

    @Value("${github.token.url}")
    private String tokenUrl;
    @Value("${github.user.url}")
    private String userUrl;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.client.uri}")
    private String clientUri;


    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code")String code, @RequestParam(name = "state") String state, HttpServletRequest request){
       // String url = "https://github.com/login/oauth/access_token";//github接口
        HttpSession session = request.getSession();
        AccessToken accessToken = new AccessToken();
        accessToken.setClient_id(clientId);
        accessToken.setClient_secret(clientSecret);
        accessToken.setCode(code);
        accessToken.setRedirect_uri(clientUri);
        accessToken.setState(state);
        String token = HttpUtils.httpPost(accessToken, tokenUrl);
        String url2 =userUrl+token;
        GithubUser githubUser = HttpUtils.httpGet(url2);
        if (githubUser!=null){
            //用户不为空，登录成功
            session.setAttribute("user",githubUser);
            return "redirect:/";
        }else {
            //用户信息为空，登录失败，重新登录
            return "redirect:/";
        }
    }
}
