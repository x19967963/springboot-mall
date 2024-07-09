package com.junhan.springbootmall.dao.Impl;

import com.junhan.springbootmall.dao.UserDao;
import com.junhan.springbootmall.dto.UserRegisterRequest;
import com.junhan.springbootmall.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.junhan.springbootmall.model.User;

import java.time.LocalDateTime;

@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        return user;
    }

    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {
        User user = convertToUser(userRegisterRequest);
        userRepository.save(user);
        return user.getUserId();
    }

    private User convertToUser(UserRegisterRequest userRegisterRequest){
        User user = new User();
        user.setEmail(userRegisterRequest.getEmail());
        user.setPassword(userRegisterRequest.getPassword());
        user.setLastModifiedDate(LocalDateTime.now());
        return user;

    }
}
