package com.xxxx.supermarket.dao;

import com.xxxx.supermarket.base.BaseMapper;
import com.xxxx.supermarket.entity.Customer;

import java.util.List;

public interface CustomerMapper extends BaseMapper<Customer,Integer> {

    List<Customer> allCustomers();
}