package com.xxxx.supermarket.entity;

import lombok.Data;

@Data
public class SaleListGoods {
    private Integer id;//主键

    private String code;//商品编码

    private String model;//商品型号

    private String name;//商品名称

    private Integer num=0;//数量

    private Double price;//单价

    private Double total;//总价

    private String unit;//单位

    private Integer saleListId;//销售单

    private Integer typeId;//商品类别

    private Integer goodsId;//商品id

}