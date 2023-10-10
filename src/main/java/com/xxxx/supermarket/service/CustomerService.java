package com.xxxx.supermarket.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.dao.CustomerMapper;
import com.xxxx.supermarket.entity.Customer;
import com.xxxx.supermarket.query.QueryCustomer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService extends BaseService<Customer,Integer> {
    @Resource
    private CustomerMapper baseMapper;

    public Map<String,Object> queryByParams(QueryCustomer queryCustomer){
        Map<String,Object> map = new HashMap<>();
        // 开启分页            当前是第几页                 每页显示几个
        PageHelper.startPage(queryCustomer.getPage(), queryCustomer.getLimit());
        /*//得到对应的分页对象
        PageInfo<SaleChance> pageInfo = new PageInfo<>(saleChanceMapper.selectByParams(saleChanceQuery));*/
        PageInfo<Customer> pageInfo= new PageInfo<>(baseMapper.selectByParams(queryCustomer));
        // 设置map对象
        map.put("code","0");
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        // 设置分页好的列表
        map.put("data",pageInfo.getList());
        return map;
    }

    public List<Customer> allCustomers() {
       return baseMapper.allCustomers();
    }
}
