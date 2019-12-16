package com.flow.snow.snow.service.impl;

import com.flow.snow.snow.dao.UserDao;
import com.flow.snow.snow.entity.User;
import com.flow.snow.snow.mapper.UserMapper;
import com.flow.snow.snow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public List<User> getUserByOpenid(String openid) {
        return userDao.findByOpenid(openid);
    }

    @Override
    public List<User> login(String phoneNumber, String password) {
        return userDao.findByPhoneNumberAndPassword(phoneNumber, password);
    }
}
