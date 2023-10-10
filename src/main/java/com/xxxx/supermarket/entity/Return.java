package com.xxxx.supermarket.entity;

import java.util.Date;

public class Return {
    private Integer id;

    private String goodsName;

    private String provider;

    private Integer allRetPrice;

    private Integer retNum;

    private Date retTime;

    private String operatePerson;

    private String remark;

    private Integer isValid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider == null ? null : provider.trim();
    }

    public Integer getAllRetPrice() {
        return allRetPrice;
    }

    public void setAllRetPrice(Integer allRetPrice) {
        this.allRetPrice = allRetPrice;
    }

    public Integer getRetNum() {
        return retNum;
    }

    public void setRetNum(Integer retNum) {
        this.retNum = retNum;
    }

    public Date getRetTime() {
        return retTime;
    }

    public void setRetTime(Date retTime) {
        this.retTime = retTime;
    }

    public String getOperatePerson() {
        return operatePerson;
    }

    public void setOperatePerson(String operatePerson) {
        this.operatePerson = operatePerson == null ? null : operatePerson.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
}