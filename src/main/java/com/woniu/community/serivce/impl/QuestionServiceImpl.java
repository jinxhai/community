package com.woniu.community.serivce.impl;

import com.woniu.community.dao.QuestionMapper;
import com.woniu.community.dao.UserMapper;
import com.woniu.community.entity.Pagination;
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
    public Pagination findList(Integer page, Integer size) {
        Pagination pagination = new Pagination();
        Integer offset = size *(page-1);
        List<Question> questions = questionMapper.listAll(offset,size);
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreatorId());
            question.setUser(user);
        }
        Integer totalCount = questionMapper.selectQuestionListCount();
        pagination.setQuestions(questions);
        pagination.setPagination(totalCount,page,size);
        return pagination;
    }

    @Override
    public Pagination findListByUserId(Integer id,Integer page,Integer size) {
        Pagination pagination = new Pagination();
        Integer offset = size *(page-1);
        List<Question> questions = questionMapper.selectList(id,offset,size);
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreatorId());
            question.setUser(user);
        }

        Integer totalCount = questionMapper.selectQuestionCount(id);
        pagination.setQuestions(questions);
        pagination.setPagination(totalCount,page,size);
        return pagination;
    }
}
