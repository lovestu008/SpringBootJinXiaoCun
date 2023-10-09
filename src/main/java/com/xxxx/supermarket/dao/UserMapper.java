package com.xxxx.supermarket.dao;

import com.xxxx.supermarket.base.BaseMapper;
import com.xxxx.supermarket.entity.User;

public interface UserMapper extends BaseMapper<User,Integer> {

    User selectUserByName(String userName);

    User selectUserByEmail(String email);
}