package com.xxxx.supermarket.service;

import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.dao.UserMapper;
import com.xxxx.supermarket.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService extends BaseService<User,Integer> {


    @Resource
    private UserMapper userMapper;



}
