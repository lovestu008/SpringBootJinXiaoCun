package com.xxxx.supermarket.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.dao.CustomerReturnListGoodsMapper;
import com.xxxx.supermarket.entity.CustomerReturnListGoods;
import com.xxxx.supermarket.entity.SaleListGoods;
import com.xxxx.supermarket.query.CustomerReturnListGoodsQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Service
public class CustomerReturnListGoodsService extends BaseService<CustomerReturnListGoods,Integer> {

    @Resource
    private CustomerReturnListGoodsMapper customerReturnListGoodsMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> queryCustomerReturnListGoodsByCustomerReturnListId(CustomerReturnListGoodsQuery query) {
        Map<String, Object> map = new HashMap<>();
        //开启分页
        PageHelper.startPage(query.getPage(), query.getLimit());
        System.out.println(query.getPage()+"......."+query.getLimit());
        //得到对应分页对象
        PageInfo<CustomerReturnListGoods> pageInfo = new PageInfo<>(customerReturnListGoodsMapper.queryCustomerReturnListGoodsByCustomerReturnListId(query.getCustomerReturnListId()));
        //设置map对象
        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());
        //将分页好的列表存入map
        map.put("data", pageInfo.getList());
        return map;
    }
}
