package com.xxxx.supermarket.dao;

import com.xxxx.supermarket.base.BaseMapper;
import com.xxxx.supermarket.entity.RoleMenu;

public interface RoleMenuMapper extends BaseMapper<RoleMenu,Integer> {

    int countRoleMenuByMenuId(Integer id);

    int  deleteByMenuId(Integer id);
    Integer countPermissionByRoleId(Integer roleId);

    void deleteMenuByRoleId(Integer roleId);
}