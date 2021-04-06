package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User checkUser(String name,String pwd){
        return userMapper.checkUser(name,pwd);
    }
}