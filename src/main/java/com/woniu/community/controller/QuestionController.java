package com.woniu.community.controller;

import com.woniu.community.entity.Question;
import com.woniu.community.serivce.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description: //TODO $
 * @Author jinxuehai
 * @Date $ $
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Integer id, Model model){
        Question question = questionService.findById(id);
        model.addAttribute("question",question);
        return "question";
    }
}
