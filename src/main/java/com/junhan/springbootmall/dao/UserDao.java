package com.junhan.springbootmall.dao;

import com.junhan.springbootmall.dto.UserRegisterRequest;
import com.junhan.springbootmall.model.User;

public interface UserDao {
    User getUserById(Integer userId);
    User getUserByEmail(String email);
    Integer createUser(UserRegisterRequest userRegisterRequest);

}
