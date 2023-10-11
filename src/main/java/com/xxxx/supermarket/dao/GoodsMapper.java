package com.xxxx.supermarket.dao;

import com.xxxx.supermarket.base.BaseMapper;
import com.xxxx.supermarket.entity.Goods;

public interface GoodsMapper extends BaseMapper<Goods,Integer> {
    /**
     * 通过角色名查询角色记录
     * @param goodsName
     * @return
     */
    public Goods selectByGoodsName(String goodsName);
    /**
     * 删除单条记录
     * @param id
     * @return
     */
    public Integer deleteGoods(Integer id);

    Goods queryGoodsByCode(Integer code);

    Goods getGoodsById(Integer gid);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    public Integer deleteAllGoods(Integer[] ids);

}