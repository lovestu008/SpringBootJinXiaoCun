package com.xxxx.supermarket.service;

import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.dao.MenuMapper;
import com.xxxx.supermarket.entity.Menu;
import com.xxxx.supermarket.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuService extends BaseService<Menu,Integer> {
    @Resource
    private MenuMapper menuMapper;


    public Map<String, Object> menuList() {
        Map<String,Object> result = new HashMap<>();
        List<Menu> menus = menuMapper.queryMenus();
        result.put("count",menus.size());
        result.put("data",menus);
        result.put("code",0);
        result.put("msg","");
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addMenu(Menu menu) {
        //参数校验
        AssertUtil.isTrue(StringUtils.isBlank(menu.getName()),"模块名为不能为空");
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
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateMenu() {

    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteMenu() {

    }
}
