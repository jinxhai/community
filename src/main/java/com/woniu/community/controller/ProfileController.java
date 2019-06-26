package com.woniu.community.controller;

import com.woniu.community.dao.UserMapper;
import com.woniu.community.entity.Pagination;
import com.woniu.community.entity.User;
import com.woniu.community.serivce.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description: //TODO $
 * @Author jinxuehai
 * @Date $ $
 */
@Controller
public class ProfileController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action, Model model, HttpServletRequest request, @RequestParam(name = "page",defaultValue = "1") Integer page, @RequestParam(name = "size",defaultValue = "5") Integer size){
        User user = (User)request.getSession().getAttribute("user");
        if (user != null){
            //加载发现问题
            Pagination pagination = questionService.findListByUserId(user.getUserId(),page,size);
            model.addAttribute("pagination",pagination);
        }else {
            return "redirect:/";
        }

        if ("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if ("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        return "profile";
    }
}
