package com.xxxx.supermarket.service;

import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.dao.PurchaseMapper;
import com.xxxx.supermarket.entity.Purchase;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class PurchaseService extends BaseService<Purchase,Integer> {
    @Resource
    private PurchaseMapper purchaseMapper;

    public List<Map<String,Object>> selectAllProvider(){
        return purchaseMapper.selectAllProvider();
    }
}
