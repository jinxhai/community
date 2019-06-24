package com.woniu.community.serivce.impl;

import com.woniu.community.dao.QuestionMapper;
import com.woniu.community.dao.UserMapper;
import com.woniu.community.entity.Question;
import com.woniu.community.entity.User;
import com.woniu.community.serivce.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: //TODO $
 * @Author jinxuehai
 * @Date $ $
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;
    @Override
    public List<Question> findListByUserId(Integer Id) {

        List<Question> questions = questionMapper.selectList(Id);
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreatorId());
            question.setUser(user);
        }

        return questions;
    }
}
