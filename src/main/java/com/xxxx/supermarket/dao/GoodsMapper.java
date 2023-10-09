package com.xxxx.supermarket.dao;

import com.xxxx.supermarket.base.BaseMapper;
import com.xxxx.supermarket.entity.Goods;

public interface GoodsMapper extends BaseMapper<Goods,Integer> {

    Goods queryGoodsByCode(Integer code);
}