package com.junhan.springbootmall.service.Impl;

import com.junhan.springbootmall.dao.UserDao;
import com.junhan.springbootmall.dto.UserRegisterRequest;
import com.junhan.springbootmall.model.User;
import com.junhan.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        //檢查註冊email是否已創立
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());
        if (user != null) {
            log.warn("該eamil {} 已經被註冊", userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //創立帳號
        return userDao.createUser(userRegisterRequest);
    }
}
