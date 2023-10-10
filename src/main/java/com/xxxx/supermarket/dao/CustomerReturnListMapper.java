package com.xxxx.supermarket.dao;

import com.xxxx.supermarket.base.BaseMapper;
import com.xxxx.supermarket.entity.CustomerReturnList;
import com.xxxx.supermarket.query.CustomerReturnListQuery;
import io.swagger.models.auth.In;

import java.util.List;

public interface CustomerReturnListMapper extends BaseMapper<CustomerReturnList,Integer> {


    //通过退货单号查询所有对应的退货记录
    CustomerReturnList queryCustomerReturnListByCustomerReturnNumber(String customerReturnNumber);

    List<CustomerReturnList> querySaleListByParams(CustomerReturnListQuery query);

    Integer deleteById(Integer id);
}