package com.woniu.community.dao;

import com.woniu.community.entity.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,description,create_time,modified_time,creator_id,tag)values(#{title},#{description},#{createTime},#{modifiedTime},#{creatorId},#{tag})")
    void create(Question question);

    @Select("select q.* , u.* from question q join user u on q.creator_id=u.user_id where q.creator_id=#{id}")
    List<Question> selectList(Integer id);
}
