package com.xxxx.supermarket.dao;

import com.xxxx.supermarket.base.BaseMapper;
import com.xxxx.supermarket.entity.GoodsUnit;

import java.util.List;
import java.util.Map;


public interface GoodsUnitMapper extends BaseMapper<GoodsUnit,Integer> {

    /**
     * 查询所有的商品单位
     * @return
     */
    List<Map<String, Object>> allGoodsUnits();

}