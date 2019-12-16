package com.flow.snow.snow.dao;

import com.flow.snow.snow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {
    List<User> findByOpenid(String openid);
    List<User> findByPhoneNumberAndPassword(String phoneNumber, String password);
}
