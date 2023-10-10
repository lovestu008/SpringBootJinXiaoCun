package com.xxxx.supermarket.service;

import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.dao.MenuMapper;
import com.xxxx.supermarket.dao.RoleMenuMapper;
import com.xxxx.supermarket.entity.Menu;
import com.xxxx.supermarket.model.TreeModel;
import com.xxxx.supermarket.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuService extends BaseService<Menu,Integer> {
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private RoleMenuMapper roleMenuMapper;

    /**
     * 查询菜单集合
     * @return
     */
    public Map<String, Object> menuList() {
        Map<String,Object> result = new HashMap<>();
        List<Menu> menus = menuMapper.queryMenus();
        result.put("count",menus.size());
        result.put("data",menus);
        result.put("code",0);
        result.put("msg","");
        return result;
    }

    /**
     * 菜单添加模块
     * @param menu
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addMenu(Menu menu) {
        //参数校验
        AssertUtil.isTrue(StringUtils.isBlank(menu.getName()),"菜单名为不能为空");
        Integer grade = menu.getGrade();
        AssertUtil.isTrue(null == grade || !(grade == 0 || grade == 1|| grade ==2),"菜单层级不合法");
        AssertUtil.isTrue(null != menuMapper.selectMenuByGradeAndMenuName(menu.getGrade(),menu.getName()),"该层级下菜单重复");
        if (grade == 1){
            AssertUtil.isTrue(StringUtils.isBlank(menu.getUrl()),"请指定二级菜单URL值");
            AssertUtil.isTrue(null != menuMapper.selectMenuByGradeAndUrl(menu.getGrade(),menu.getUrl()),"");
        }
        if (grade != 0 ){
            Integer parentId = menu.getpId();
            AssertUtil.isTrue(null == parentId || null == selectByPrimaryKey(parentId),"请指定上级菜单");
        }
        if (grade == 0 ){
            menu.setpId(-1);
        }
        AssertUtil.isTrue(StringUtils.isBlank(menu.getAclValue()),"请输入权限码");
        //权限码不可重复
        AssertUtil.isTrue(null != menuMapper.selectMenuByAclValue(menu.getAclValue()),"权限码不可重复");
        menu.setIsDel(0);
        //执行添加操作
        AssertUtil.isTrue(insertSelective(menu)<1,"菜单添加失败");
    }

    /**
     * 菜单修改模块
     * @param menu
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateMenu(Menu menu) {
        AssertUtil.isTrue(null == menu.getId() ,"待更新记录不存在");
        Menu temp = menuMapper.selectByPrimaryKey(menu.getId());
        AssertUtil.isTrue(null == temp,"待更新记录不存在");
        //层级
        Integer grade = menu.getGrade();
        AssertUtil.isTrue(null == grade ||!(grade == 0 || grade == 1 || grade == 2), "菜单层级不合法！");
        AssertUtil.isTrue(StringUtils.isBlank(menu.getName()),"菜单名为不能为空");
        temp = menuMapper.selectMenuByGradeAndMenuName(grade, menu.getName());
        if (temp != null){
            AssertUtil.isTrue(!(temp.getId()).equals(menu.getId()),"该层级下菜单名已经存在");
        }
        if (grade == 1){
            AssertUtil.isTrue(StringUtils.isBlank(menu.getUrl()),"菜单url不能为空");
            temp = menuMapper.selectMenuByGradeAndUrl(grade, menu.getUrl());
            if (temp != null){
                AssertUtil.isTrue(!(temp.getId().equals(menu.getId())),"该层级下菜单地址已经存在");
            }
        }
        AssertUtil.isTrue(StringUtils.isBlank(menu.getAclValue()),"权限码不能为空");
        temp = menuMapper.selectMenuByAclValue(menu.getAclValue());
        if (temp!=null){
            AssertUtil.isTrue(!(temp.getId().equals(menu.getId())),"权限码已经存在");
        }
        AssertUtil.isTrue(menuMapper.updateByPrimaryKeySelective(menu)<1,"菜单更新失败");
    }

    /**
     * 删除菜单
     * @param id
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteMenu(Integer id) {
        Menu temp = menuMapper.selectByPrimaryKey(id);
        AssertUtil.isTrue(id == null || null == temp,"要删除的模块不存在");
        //如果存在子菜单 不允许删除
        int count = menuMapper.countSubMenuByParentId(id);
        AssertUtil.isTrue(count > 0 ,"存在子菜单，不允许删除");

        //权限表
        count = roleMenuMapper.countRoleMenuByMenuId(id);
        if (count > 0){
            AssertUtil.isTrue(roleMenuMapper.deleteByMenuId(id) < count,"菜单删除失败");
        }
        temp.setIsDel(1);
        AssertUtil.isTrue(updateByPrimaryKeySelective(temp)<1,"菜单删除失败");
    }

    /**
     * 查询所有菜单
     * @return
     */
    public List<TreeModel> selectAllMenus(Integer roleId) {
        // 查询所有的资源列表
        List<TreeModel> treeModelList = menuMapper.selectAllMenus();
        // 查询指定角色已经授权过的资源列表 (查询角色拥有的资源ID)
        List<Integer> permisssionIds = roleMenuMapper.queryRoleHasMenuIdsByRoleId(roleId);
        permisssionIds.forEach(System.out::println);
        if (permisssionIds != null && permisssionIds.size() > 0 ){
            // 循环所有的资源列表，判断用户拥有的资源ID中是否有匹配的，如果有，则设置checked属性为true
            treeModelList.forEach(treeModel -> {
                System.out.println("======"+treeModel.getId());
                if (permisssionIds.contains(treeModel.getId())){
                    treeModel.setChecked(true);
                }
            });
        }
        return treeModelList;
    }
}
