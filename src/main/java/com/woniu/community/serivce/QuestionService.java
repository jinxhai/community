package com.woniu.community.serivce;

import com.woniu.community.entity.Pagination;
import com.woniu.community.entity.Question;

import java.util.List;

public interface QuestionService {
    public Pagination findList(Integer page, Integer size);

    public Pagination findListByUserId(Integer id, Integer page, Integer size);
}
