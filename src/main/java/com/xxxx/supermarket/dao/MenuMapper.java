package com.xxxx.supermarket.dao;

import com.xxxx.supermarket.base.BaseMapper;
import com.xxxx.supermarket.entity.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu,Integer> {

    List<Menu> queryMenus();
}