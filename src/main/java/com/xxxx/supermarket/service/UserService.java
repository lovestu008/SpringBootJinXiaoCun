package com.xxxx.supermarket.service;

import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.base.ResultInfo;
import com.xxxx.supermarket.dao.UserMapper;
import com.xxxx.supermarket.entity.User;
import com.xxxx.supermarket.model.UserModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService extends BaseService<User,Integer> {

    @Resource
    private UserMapper userMapper;

    /**
     * 用户登录模块service层
     * @param userName
     * @param userPwd
     * @return
     */



}
