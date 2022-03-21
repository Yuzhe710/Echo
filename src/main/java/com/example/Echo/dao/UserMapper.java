package com.example.Echo.dao;

import com.example.Echo.entity.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper // MyBatis注解bean
public interface UserMapper {

    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    int insertUser(User user);

    int updateStatus(int id, int status);

    int updateHeader(int id, String headerUrl);

    int updatePassword(int id, String password);


}
