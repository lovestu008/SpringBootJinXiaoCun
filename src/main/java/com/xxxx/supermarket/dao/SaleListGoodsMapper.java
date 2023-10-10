package com.xxxx.supermarket.dao;

import com.xxxx.supermarket.base.BaseMapper;
import com.xxxx.supermarket.entity.SaleListGoods;

import java.util.List;

public interface SaleListGoodsMapper extends BaseMapper<SaleListGoods,Integer> {


    /**
     * 通过订单编号判断属于该订单的记录数
     * @param id
     * @return
     */
    List<SaleListGoods> querySaleListGoodsBySaleListId(Integer id);
}