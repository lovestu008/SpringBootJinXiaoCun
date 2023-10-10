package com.xxxx.supermarket.dao;

import com.xxxx.supermarket.base.BaseMapper;
import com.xxxx.supermarket.entity.Customer;
import com.xxxx.supermarket.entity.Provider;

import java.util.List;

public interface ProviderMapper extends BaseMapper<Provider, Integer> {

    List<Provider> allProvider();
}