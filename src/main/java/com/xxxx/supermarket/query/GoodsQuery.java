package com.xxxx.supermarket.query;


import com.xxxx.supermarket.base.BaseQuery;


/**
 * 商品管理查询类
 */

public class GoodsQuery extends BaseQuery {


    //商品管理 多条件查询
    private String goodsName;
    private Integer typeId;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}