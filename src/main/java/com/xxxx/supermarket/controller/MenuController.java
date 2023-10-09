package com.xxxx.supermarket.controller;

import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
    public String toUpdateMenuPage(){
        return "menu/update";
    }

    @RequestMapping("toAddMenuPage")
    public String toAddMenuPage(){
        return "menu/add";
    }


}
