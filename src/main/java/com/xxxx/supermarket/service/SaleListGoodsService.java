package com.xxxx.supermarket.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.dao.SaleListGoodsMapper;
import com.xxxx.supermarket.entity.SaleList;
import com.xxxx.supermarket.entity.SaleListGoods;
import com.xxxx.supermarket.query.SaleListGoodsQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class SaleListGoodsService extends BaseService<SaleListGoods,Integer> {
    
    @Resource
    private SaleListGoodsMapper saleListGoodsMapper;


    /**
     * 根据SaleListGoodsQuery获取销售单详情列表
     * @param query
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> saleListGoodsList(SaleListGoodsQuery query) {
        Map<String, Object> map = new HashMap<>();
        //开启分页
        PageHelper.startPage(query.getPage(), query.getLimit());
        System.out.println(query.getPage()+"......."+query.getLimit());
        //得到对应分页对象
        PageInfo<SaleListGoods> pageInfo = new PageInfo<>(saleListGoodsMapper.querySaleListGoodsBySaleListId(query.getSaleListId()));
        //设置map对象
        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());
        //将分页好的列表存入map
        map.put("data", pageInfo.getList());
        return map;
    }
}
