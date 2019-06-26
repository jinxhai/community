package com.woniu.community.controller;

import com.woniu.community.entity.Pagination;
import com.woniu.community.entity.User;
import com.woniu.community.serivce.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 加载首页
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,@RequestParam(name = "page",defaultValue = "1") Integer page,@RequestParam(name = "size",defaultValue = "5") Integer size){

        User user = (User) request.getSession().getAttribute("user");
        if (user != null){
            request.getSession().setAttribute("user", user);
        }
        //加载发现问题
        Pagination pagination = questionService.findList(page, size);
        model.addAttribute("pagination",pagination);

        return "index";
    }
}
