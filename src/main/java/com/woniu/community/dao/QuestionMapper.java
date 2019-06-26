package com.woniu.community.dao;

import com.woniu.community.entity.Pagination;
import com.woniu.community.entity.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,description,create_time,modified_time,creator_id,tag)values(#{title},#{description},#{createTime},#{modifiedTime},#{creatorId},#{tag})")
    void create(Question question);

    @Select("select * from question where creator_id=#{id} limit #{offset},#{size}")
    List<Question> selectList(@Param(value = "id") Integer id, @Param(value = "offset") Integer offset,@Param(value = "size") Integer size);

    @Select("select count(1) from question where creator_id=#{id}")
    Integer selectQuestionCount(Integer id);

    @Select("select * from question  limit #{offset},#{size}")
    List<Question> listAll(@Param(value = "offset") Integer offset,@Param(value = "size")Integer size);

    @Select("select count(1) from question")
    Integer selectQuestionListCount();
}
