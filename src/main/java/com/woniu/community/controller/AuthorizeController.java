package com.woniu.community.controller;

import com.woniu.community.dao.UserMapper;
import com.woniu.community.entity.AccessToken;
import com.woniu.community.entity.GithubUser;
import com.woniu.community.entity.Question;
import com.woniu.community.entity.User;
import com.woniu.community.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

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

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code")String code, @RequestParam(name = "state") String state, HttpServletRequest request, HttpServletResponse response){
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
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setUserName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setCreateTime(System.currentTimeMillis());
            user.setModifiedTime(user.getModifiedTime());
            user.setImgUrl(githubUser.getAvatar_url());
            userMapper.insertUser(user);
            response.addCookie(new Cookie("token",user.getToken()));
            //用户不为空，登录成功
            return "redirect:/";
        }else {
            //用户信息为空，登录失败，重新登录
            return "redirect:/";
        }
    }
}
