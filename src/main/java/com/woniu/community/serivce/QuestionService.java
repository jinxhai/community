package com.woniu.community.serivce;

import com.woniu.community.entity.Question;

import java.util.List;

public interface QuestionService {
    public List<Question> findListByUserId(Integer Id);
}
