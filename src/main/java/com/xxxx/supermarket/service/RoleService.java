package com.xxxx.supermarket.service;

import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.dao.RoleMapper;
import com.xxxx.supermarket.dao.RoleMenuMapper;
import com.xxxx.supermarket.entity.Role;
import com.xxxx.supermarket.entity.RoleMenu;
import com.xxxx.supermarket.utils.AssertUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RoleService extends BaseService<Role, Integer> {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleMenuMapper roleMenuMapper;
    /**
     * 添加角色模块
     *
     * @param role
     */


    @Transactional(propagation = Propagation.REQUIRED)
    public void addRole(Role role) {
        AssertUtil.isTrue(null == role.getName(), "角色名不能为空");
        AssertUtil.isTrue(null == role.getBz(), "备注不能为空");
        AssertUtil.isTrue(null != roleMapper.selectByRoleName(role.getName()), "角色名重复");
        role.setIsDel(0);
        AssertUtil.isTrue(roleMapper.insertSelective(role) < 1, "角色添加失败");
    }

    /**
     * 更新角色模块
     *
     * @param role
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateRole(Role role) {
        AssertUtil.isTrue(null == role.getName(), "角色名不能为空");
        AssertUtil.isTrue(null == role.getBz(), "备注不能为空");
        //判断角色名是否为空
        Role temp = roleMapper.selectByRoleName(role.getName());
        AssertUtil.isTrue(temp != null && !temp.getId().equals(role.getId()), "角色名重复");
        AssertUtil.isTrue(roleMapper.updateByPrimaryKeySelective(role) < 1,"角色更新失败");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteRole(Integer id) {
        AssertUtil.isTrue( null == id,"请选择要删除的角色");
        Role role = roleMapper.selectByPrimaryKey(id);
        AssertUtil.isTrue(role == null,"要删除的角色不存在");
        AssertUtil.isTrue(roleMapper.deleteByPrimaryKey(id) < 1,"角色删除失败");
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void addGrant(Integer roleId, Integer[] mIds) {
        //通过权限ID查询对应权限记录
       Integer count =  roleMenuMapper.countPermissionByRoleId(roleId);
        if (count>0){
            roleMenuMapper.deleteRoleMenuByRoleId(roleId);
        }
        if (mIds != null && mIds.length>0){
            List<RoleMenu> roleMenuList = new ArrayList<>();
            for (Integer mId:mIds){
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setMenuId(mId);
                roleMenu.setRoleId(roleId);
                roleMenu.setAclValue(roleMenuMapper.selectByPrimaryKey(mId).getAclValue());
            //将对象设置到集合中
                roleMenuList.add(roleMenu);
            }
            //批量添加
            AssertUtil.isTrue(roleMenuMapper.insertBatch(roleMenuList)!=roleMenuList.size(),"角色授权失败！");
        }
    }
    //查询所有的角色列表
    public List<Map<String, Object>> queryAllRoles(Integer userId) {
        return roleMapper.queryAllRoles(userId);
    }
}
