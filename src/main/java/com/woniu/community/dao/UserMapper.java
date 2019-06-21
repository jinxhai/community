package com.woniu.community.dao;

import com.woniu.community.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {

    @Insert("insert into user (account_id,user_name,token,create_time,modified_time)values(#{accountId},#{userName},#{token},#{createTime},#{modifiedTime})")
    void insertUser(User user);

    @Select("select user_id userId,account_id accountId,user_name userName,token,create_time createTime,modified_time modifiedTime from user where token = #{token}")
    User findByToken(@Param("token") String token);

}
