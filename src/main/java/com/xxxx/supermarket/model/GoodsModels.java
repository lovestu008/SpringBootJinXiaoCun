package com.xxxx.supermarket.model;

import lombok.Data;

@Data
public class GoodsModels {

    //商品id
    private Integer id;

    //商品编码
    private String code;

    //商品型号
    private String model;

    //商品名称
    private String name;

    //数量
    private Integer num;

    //单价
    private Double price;

    //总价
    private Double total;

    //单位
    private String unit;


    //商品类别
    private Integer typeId;

    //商品类别名称
    private String typeName;

    //上次进价
    private Float lastPurchasingPrice;

    //库存
    private Integer inventoryQuantity;
}
