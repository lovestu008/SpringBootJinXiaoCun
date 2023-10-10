package com.xxxx.supermarket.controller;

import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.base.ResultInfo;
import com.xxxx.supermarket.entity.Menu;
import com.xxxx.supermarket.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("menu")
public class MenuController extends BaseController {
    @Resource
    private MenuService menuService;

    @RequestMapping("index")
    public String index(){
        return "menu/menu";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> menuList(){
        return menuService.menuList();
    }

    @RequestMapping("toUpdateMenuPage")
    public String toUpdateMenuPage(HttpServletRequest request,Integer id){
        request.setAttribute("menu",menuService.selectByPrimaryKey(id));
        return "menu/update";
    }

    @RequestMapping("toAddMenuPage")
    public String toAddMenuPage(HttpServletRequest request,Integer grade,Integer parentId){
        request.setAttribute("grade",grade);
        request.setAttribute("pId",parentId);
        return "menu/add";
    }

    @RequestMapping("add")
    @ResponseBody
    public ResultInfo addMenu(Menu menu){
        menuService.addMenu(menu);
        return success("添加菜单成功");
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateMenu(Menu menu){
        menuService.updateMenu(menu);
        return success("更新菜单成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteMenu(Integer id){
        menuService.deleteMenu(id);
        return success("菜单删除成功");
    }
}
