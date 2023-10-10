package com.xxxx.supermarket.model;

import com.xxxx.supermarket.entity.Goods;

public class GoodsModel extends Goods {
    private Integer id;//编号
    private String code;//商品编码
    private String name;//商品名称
    private String model;//商品型号
    private String typeName;//商品类别
    private String unitName;//单位
    private Float purchasingPrice;//采购价格
    private Float sellingPrice;//出售价格
    private Integer minNum;//库存下限
    private String producer;//生产厂商
    private int isDel;

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Float getPurchasingPrice() {
        return purchasingPrice;
    }

    public void setPurchasingPrice(Float purchasingPrice) {
        this.purchasingPrice = purchasingPrice;
    }

    public Float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getMinNum() {
        return minNum;
    }

    public void setMinNum(Integer minNum) {
        this.minNum = minNum;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
}
