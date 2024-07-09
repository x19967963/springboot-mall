package com.junhan.springbootmall.service;

import com.junhan.springbootmall.dto.UserRegisterRequest;
import com.junhan.springbootmall.model.User;

public interface UserService {
    User getUserById(Integer userId);
    Integer register(UserRegisterRequest userRegisterRequest);
}
