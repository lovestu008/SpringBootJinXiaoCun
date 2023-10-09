package com.xxxx.supermarket.model;

import com.xxxx.supermarket.entity.SaleListGoods;

public class SaleListGoodsModel extends SaleListGoods {
    private Float allTotal;

    public Float getAllTotal() {
        return allTotal;
    }

    public void setAllTotal(Float allTotal) {
        this.allTotal = allTotal;
    }
}
