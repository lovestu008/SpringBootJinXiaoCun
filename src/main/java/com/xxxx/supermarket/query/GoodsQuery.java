package com.xxxx.supermarket.query;


import com.xxxx.supermarket.base.BaseQuery;
import lombok.Data;

import java.util.List;


/**
 * 商品管理查询类
 */
@Data
public class GoodsQuery extends BaseQuery {


    //商品管理 多条件查询
    private String goodsName;
    private Integer typeId =1;

    private List<Integer> typeIds;


    // 查询类型 区分库存量是否大于0查询
    /**
     * 1 库存量=0
     * 2 库存量>0
     * 3 库存量<库存下限
     */
    private Integer type;

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

    public List<Integer> getTypeIds() {
        return typeIds;
    }

    public void setTypeIds(List<Integer> typeIds) {
        this.typeIds = typeIds;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}