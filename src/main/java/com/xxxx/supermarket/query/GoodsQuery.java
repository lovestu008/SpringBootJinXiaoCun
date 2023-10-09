package com.xxxx.supermarket.query;


import com.xxxx.supermarket.base.BaseQuery;


/**
 * 商品管理查询类
 */

public class GoodsQuery extends BaseQuery {


    //商品管理 多条件查询
    private String name;//商品名称


    private Integer typeId;//类别ID

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}