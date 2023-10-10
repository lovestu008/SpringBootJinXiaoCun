package com.xxxx.supermarket.dao;

import com.xxxx.supermarket.base.BaseMapper;
import com.xxxx.supermarket.entity.Purchase;
import com.xxxx.supermarket.entity.Return;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public interface PurchaseMapper extends BaseMapper<Purchase,Integer> {
    List<Map<String,Object>> selectAllProvider();
    List<Map<String, Object>> selectAllGoodsName();

    String selectProviderByGoodsNameFromGoods(String goodsName);

    List<Map<String, Object>> selectAllGoodsNameById();

    Integer selectNumByGoodsName(String goodsName);

    int updateGoodsNum(Integer goodsNum,String goodsName);

    Integer selectPurchasingPriceByGoodsName(String goodsName);

    Integer updateGoodsNumAndLastPurchasingPrice(String goodsName, Integer goodsNum,Integer purchasingPrice,Integer lastPurchasingPrice);
}