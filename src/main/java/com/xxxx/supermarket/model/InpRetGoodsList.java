package com.xxxx.supermarket.model;

public class InpRetGoodsList {
    private Integer purchaseId;

    private String operatePerson;
    private String goodsName;
    private String provider;
    //商品库存
    private Integer goodsNum;

    //退货数量
    private Integer retNum;

    //退货备注
    private String remark;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Integer getRetNum() {
        return retNum;
    }

    public void setRetNum(Integer retNum) {
        this.retNum = retNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    @Override
    public String toString() {
        return "InpRetGoodsList{" +
                "purchaseId=" + purchaseId +
                ", operatePerson='" + operatePerson + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", provider='" + provider + '\'' +
                ", goodsNum=" + goodsNum +
                ", retNum=" + retNum +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getOperatePerson() {
        return operatePerson;
    }

    public void setOperatePerson(String operatePerson) {
        this.operatePerson = operatePerson;
    }
}
