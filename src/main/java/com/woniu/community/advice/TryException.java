package com.woniu.community.advice;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: //TODO $
 * @Author jinxuehai
 * @Date $ $
 */
@ControllerAdvice
public class TryException {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model){
        model.addAttribute("message","服务冒烟了,您稍后试试..");
        return  new ModelAndView("error");
    }


}
