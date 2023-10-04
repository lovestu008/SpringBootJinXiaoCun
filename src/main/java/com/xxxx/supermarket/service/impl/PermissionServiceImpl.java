package com.xxxx.supermarket.service.impl;

import com.xxxx.supermarket.entity.Permission;
import com.xxxx.supermarket.mapper.PermissionMapper;
import com.xxxx.supermarket.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 
 * @since 2023-03-03
 */
@Service
@Transactional
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    /**
     * 根据角色id查询对应权限
     * @param roleId
     * @return
     */
    @Override
    public List<Integer> findRolePermissionIdByRoleId(int roleId) {

        List<Integer> rolePermissionIdByRoleId = permissionMapper.findRolePermissionIdByRoleId(roleId);
        return rolePermissionIdByRoleId;
    }

    /**
     * 根据权限id删除角色权限表对应的数据
     * @param id
     */
    @Override
    public boolean removeById(Serializable id) {
        permissionMapper.deleteRolePermissionByPermisssionid(id);
        return super.removeById(id);
    }
}
