package com.xxxx.supermarket.controller;

import com.xxxx.supermarket.annotation.RequiredPermission;
import com.xxxx.supermarket.aspect.SupLog;
import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.base.ResultInfo;
import com.xxxx.supermarket.entity.Menu;
import com.xxxx.supermarket.model.TreeModel;
import com.xxxx.supermarket.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("menu")
@Slf4j
@SupLog(type = "菜单权限管理")
public class MenuController extends BaseController {
    @Resource
    private MenuService menuService;
    @RequiredPermission(code = "5030")
    @RequestMapping("index")
    public String index(){
        return "menu/menu";
    }
    @RequiredPermission(code = "5030")
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> menuList(){
        return menuService.menuList();
    }
    @RequiredPermission(code = "5030")
    @RequestMapping("toUpdateMenuPage")
    public String toUpdateMenuPage(HttpServletRequest request,Integer id){
        request.setAttribute("menu",menuService.selectByPrimaryKey(id));
        return "menu/update";
    }
    @RequiredPermission(code = "5030")
    @RequestMapping("toAddMenuPage")
    public String toAddMenuPage(HttpServletRequest request,Integer grade,Integer parentId){
        request.setAttribute("grade",grade);
        request.setAttribute("pId",parentId);
        return "menu/add";
    }
    @RequiredPermission(code = "5030")
    @RequestMapping("toAddGrantPage")
    public String toAddGrantPage(HttpServletRequest request,Integer roleId){
        request.setAttribute("roleId",roleId);
        return "role/grant";
    }
    @RequiredPermission(code = "5030")
    @RequestMapping("queryAllMenus")
    @ResponseBody
    public List<TreeModel> queryAllMenus(Integer roleId){
        return menuService.selectAllMenus(roleId);
    }


    @RequiredPermission(code = "5030")
    @RequestMapping("add")
    @ResponseBody
    @SupLog(content = "添加菜单权限")
    public ResultInfo addMenu(Menu menu){
        menuService.addMenu(menu);
        return success("添加菜单成功");
    }
    @RequiredPermission(code = "5030")
    @RequestMapping("update")
    @ResponseBody
    @SupLog(content = "修改菜单权限")
    public ResultInfo updateMenu(Menu menu){
        menuService.updateMenu(menu);
        return success("更新菜单成功");
    }
    @RequiredPermission(code = "5030")
    @RequestMapping("delete")
    @ResponseBody
    @SupLog(content = "删除菜单权限")
    public ResultInfo deleteMenu(Integer id){
        menuService.deleteMenu(id);
        return success("菜单删除成功");
    }
}
