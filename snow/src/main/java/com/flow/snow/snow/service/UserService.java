package com.flow.snow.snow.service;

import com.flow.snow.snow.entity.User;

import java.util.List;

public interface UserService {
    void insertUser(User user);
    List<User> getUserByOpenid(String openid);
    List<User> login(String phoneNumber, String password);
}
