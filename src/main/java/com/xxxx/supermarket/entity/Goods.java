package com.xxxx.supermarket.entity;

import lombok.Data;

@Data
public class Goods {
    private Integer id;//主键

    private String code;//商品编码

    private Integer inventoryQuantity;//库存数量

    private Integer minNum;//库存下限

    private String model;//商品型号

    private String name;//商品名称

    private String producer;//供应商

    private Double purchasingPrice;//采购价格

    private String remarks;//备注

    private Double sellingPrice;//出售价格

    private String unit;//商品单位

    private Integer typeId;//商品类别

    private Integer state;//商品状态

    private Float lastPurchasingPrice;//上次采购价格

    private Integer isDel;//是否删除

    private String unitName;

    private String typeName;

    private Integer saleTotal;//销售总数
}