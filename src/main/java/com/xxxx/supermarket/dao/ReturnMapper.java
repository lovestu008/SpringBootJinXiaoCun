package com.xxxx.supermarket.dao;

import com.xxxx.supermarket.base.BaseMapper;
import com.xxxx.supermarket.entity.Return;
import com.xxxx.supermarket.querys.ReturnQuery;

import java.util.List;
import java.util.Map;

public interface ReturnMapper extends BaseMapper<Return,Integer> {
    List<Map<String,Object>> selectAllProvider();
    List<Map<String, Object>> selectAllGoodsName();
}