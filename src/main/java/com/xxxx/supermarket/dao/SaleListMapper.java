package com.xxxx.supermarket.dao;

import com.xxxx.supermarket.base.BaseMapper;
import com.xxxx.supermarket.entity.SaleList;
import com.xxxx.supermarket.entity.SaleListGoods;
import com.xxxx.supermarket.query.SaleQuery;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;

public interface SaleListMapper extends BaseMapper<SaleList,Integer> {

    SaleList querySaleListBySaleNumber(String saleNumber);

    String getNextSaleNumber();

    List<SaleList> querySaleListByParams(SaleQuery query);

    Integer deleteById(Integer id);
}