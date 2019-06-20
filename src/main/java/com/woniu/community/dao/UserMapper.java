package com.woniu.community.dao;

import com.woniu.community.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {

    @Insert("insert into user (account_id,user_name,token,create_time,modified_time)values(#{accountId}#{userName},#{token},#{createTime},#{modifiedTime})")
    void insertUser(User user);
}
