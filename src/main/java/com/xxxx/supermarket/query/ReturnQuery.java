package com.xxxx.supermarket.query;

import com.xxxx.supermarket.base.BaseQuery;

public class ReturnQuery extends BaseQuery {
    //供应商
    private String provider;
    //商品名称
    private String goodsName;

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
        return "ReturnQuery{" +
                "provider='" + provider + '\'' +
                ", goodsName='" + goodsName + '\'' +
                '}';
    }
}
