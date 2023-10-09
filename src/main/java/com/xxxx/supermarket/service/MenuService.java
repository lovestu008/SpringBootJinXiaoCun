package com.xxxx.supermarket.service;

import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.dao.MenuMapper;
import com.xxxx.supermarket.entity.Menu;
import org.springframework.stereotype.Service;
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
}
