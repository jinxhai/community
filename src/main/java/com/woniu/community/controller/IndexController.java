package com.woniu.community.controller;

import com.woniu.community.dao.UserMapper;
import com.woniu.community.entity.Pagination;
import com.woniu.community.entity.Question;
import com.woniu.community.entity.User;
import com.woniu.community.serivce.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 加载首页
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,Model model,@RequestParam(name = "page",defaultValue = "1") Integer page,@RequestParam(name = "size",defaultValue = "5") Integer size){
        Cookie[] cookies = request.getCookies();
        if (cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    //查登录时插入的user信息
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                        System.out.println(user.getUserId());
                        //加载发现问题
                        Pagination pagination = questionService.findListByUserId(user.getUserId(),page,size);
                        model.addAttribute("pagination",pagination);
                    }
                    break;

                }
            }
        }




        return "index";
    }
}
