package com.xxxx.supermarket.service;

import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.dao.RoleMapper;
import com.xxxx.supermarket.entity.Role;
import com.xxxx.supermarket.utils.AssertUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleService extends BaseService<Role,Integer> {

    @Resource
    private RoleMapper roleMapper;

    public void addRole(Role role) {
        AssertUtil.isTrue(null == role.getName() ,"角色名不能为空");
        AssertUtil.isTrue(null == role.getBz() , "备注不能为空");
        AssertUtil.isTrue(null != roleMapper.selectByRoleName(role.getName()),"角色名重复");
        AssertUtil.isTrue(roleMapper.insertSelective(role) < 1,"角色添加失败");
    }

    public void updateRole(Role role) {

    }


}
