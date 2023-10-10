package com.xxxx.supermarket.dao;

import com.xxxx.supermarket.base.BaseMapper;
import com.xxxx.supermarket.entity.SaleListGoods;
import com.xxxx.supermarket.model.SaleListGoodsModel;

import java.util.List;

public interface SaleListGoodsMapper extends BaseMapper<SaleListGoods,Integer> {


    /**
     * 通过订单编号判断属于该订单的记录数
     * @param id
     * @return
     */
    List<SaleListGoods> querySaleListGoodsBySaleListId(Integer id);

    List<SaleListGoodsModel> selectSaleListGoods();
}