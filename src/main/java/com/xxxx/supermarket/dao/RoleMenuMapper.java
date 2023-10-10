package com.xxxx.supermarket.dao;

import com.xxxx.supermarket.base.BaseMapper;
import com.xxxx.supermarket.entity.RoleMenu;

import java.util.List;

public interface RoleMenuMapper extends BaseMapper<RoleMenu,Integer> {

    int countRoleMenuByMenuId(Integer id);

    int  deleteByMenuId(Integer id);
    Integer countPermissionByRoleId(Integer roleId);

    void deleteRoleMenuByRoleId(Integer roleId);

    List<Integer> queryRoleHasMenuIdsByRoleId(Integer roleId);
}