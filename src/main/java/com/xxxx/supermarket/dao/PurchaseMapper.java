package com.xxxx.supermarket.dao;

import com.xxxx.supermarket.base.BaseMapper;
import com.xxxx.supermarket.entity.Purchase;

import java.security.Provider;
import java.util.List;
import java.util.Map;

public interface PurchaseMapper extends BaseMapper<Purchase,Integer> {
        List<Map<String,Object>> selectAllProvider();
}