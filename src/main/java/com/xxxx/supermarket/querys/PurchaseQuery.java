package com.xxxx.supermarket.querys;

import com.xxxx.supermarket.base.BaseQuery;

public class PurchaseQuery extends BaseQuery {
    //供应商
    private String provider;
    //商品名称
    private String goodsName;

    //开始时间

    //结束时间
    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Override
    public String toString() {
        return "PurchaseQuery{" +
                "provider='" + provider + '\'' +
                ", goodsName='" + goodsName + '\'' +
                '}';
    }
}
