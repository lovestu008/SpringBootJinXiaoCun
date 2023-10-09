package com.xxxx.supermarket.dao;

import com.xxxx.supermarket.base.BaseMapper;
import com.xxxx.supermarket.entity.Role;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends BaseMapper<Role,Integer> {

    Role selectByRoleName(String name);
    //查询所有的角色列表(只要id和RoleName)
    List<Map<String, Object>> queryAllRoles(Integer userId);
}