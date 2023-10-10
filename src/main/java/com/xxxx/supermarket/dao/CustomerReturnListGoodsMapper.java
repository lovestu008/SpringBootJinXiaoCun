package com.xxxx.supermarket.dao;

import com.xxxx.supermarket.base.BaseMapper;
import com.xxxx.supermarket.entity.CustomerReturnListGoods;

import java.util.Collection;
import java.util.List;

public interface CustomerReturnListGoodsMapper extends BaseMapper<CustomerReturnListGoods,Integer> {


    String getNextSaleNumber();


    List<CustomerReturnListGoods> queryCustomerReturnListGoodsByCustomerReturnListId(Integer id);

}