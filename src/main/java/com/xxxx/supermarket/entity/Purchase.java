package com.xxxx.supermarket.entity;

import java.util.Date;

public class Purchase {
    private Integer id;

    private String goodsName;

    private String provider;

    private Integer inpPrice;

    private Integer allInpPrice;

    private Integer inpNum;

    private Date inpTime;

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

    public Integer getInpPrice() {
        return inpPrice;
    }

    public void setInpPrice(Integer inpPrice) {
        this.inpPrice = inpPrice;
    }

    public Integer getAllInpPrice() {
        return allInpPrice;
    }

    public void setAllInpPrice(Integer allInpPrice) {
        this.allInpPrice = allInpPrice;
    }

    public Integer getInpNum() {
        return inpNum;
    }

    public void setInpNum(Integer inpNum) {
        this.inpNum = inpNum;
    }

    public Date getInpTime() {
        return inpTime;
    }

    public void setInpTime(Date inpTime) {
        this.inpTime = inpTime;
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