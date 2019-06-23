package com.woniu.community.dao;

import com.woniu.community.entity.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,description,create_time,modified_time,creator_id,tag)values(#{title},#{description},#{createTime},#{modifiedTime},#{creatorId},#{tag})")
    void create(Question question);
}
