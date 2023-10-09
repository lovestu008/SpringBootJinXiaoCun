package com.xxxx.supermarket.dao;

import com.xxxx.supermarket.base.BaseMapper;
import com.xxxx.supermarket.entity.Role;

public interface RoleMapper extends BaseMapper<Role,Integer> {

    Role selectByRoleName(String name);
}