package com.xxxx.supermarket.query;

import com.xxxx.supermarket.base.BaseQuery;
import lombok.Data;

@Data
public class SaleListGoodsQuery extends BaseQuery {

    private Integer saleListId;

    public Integer getSaleListId() {
        return saleListId;
    }

    public void setSaleListId(Integer saleListId) {
        this.saleListId = saleListId;
    }
}
