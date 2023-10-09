package com.xxxx.supermarket.dao;

import com.xxxx.supermarket.base.BaseMapper;
import com.xxxx.supermarket.dto.TreeDto;
import com.xxxx.supermarket.entity.GoodsType;

import java.util.List;

public interface GoodsTypeMapper extends BaseMapper<GoodsType,Integer> {
    List<TreeDto> queryAllGoodsTypes();
}