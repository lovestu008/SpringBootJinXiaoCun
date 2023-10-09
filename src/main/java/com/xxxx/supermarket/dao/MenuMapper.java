package com.xxxx.supermarket.dao;

import com.xxxx.supermarket.base.BaseMapper;
import com.xxxx.supermarket.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu,Integer> {

    List<Menu> queryMenus();

    Menu selectMenuByGradeAndMenuName(@Param("grade") Integer grade, @Param("name")String name);

    Menu selectMenuByGradeAndUrl(@Param("grade")Integer grade, @Param("url")String url);

    Menu selectMenuByAclValue(String aclValue);

    int countSubMenuByParentId(Integer id);
}