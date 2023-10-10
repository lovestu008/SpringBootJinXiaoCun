package com.xxxx.supermarket.dao;

import com.xxxx.supermarket.base.BaseMapper;
import com.xxxx.supermarket.entity.UserRole;

public interface UserRoleMapper extends BaseMapper<UserRole,Integer> {

    Integer countUserRoleByUserId(Integer id);

    int deleteByUserId(Integer id);
}