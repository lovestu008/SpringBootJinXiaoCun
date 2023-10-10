package com.xxxx.supermarket.service;

import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.dao.GoodsUnitMapper;
import com.xxxx.supermarket.entity.Goods;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service
public class GoodsUnitService extends BaseService<Goods,Integer> {
    @Resource
    GoodsUnitMapper goodsUnitMapper;

    /**
     * 查询所有的商品单位
     * @return
     */
    public List<Map<String, Object>> allGoodsUnits() {
        return goodsUnitMapper.allGoodsUnits();
    }

}
