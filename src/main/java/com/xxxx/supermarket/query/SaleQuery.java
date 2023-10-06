package com.xxxx.supermarket.query;

import com.xxxx.supermarket.base.BaseQuery;

import java.util.Date;

public class SaleQuery extends BaseQuery {
    private Integer customerId;//客户id
    private Float amountPaid;//实付金额
    private Float amountPayable;//应付金额
    private Date saleDate;//销售日期
    private String remarks;//备注
    private Integer state;//是否付款

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Float getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Float amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Float getAmountPayable() {
        return amountPayable;
    }

    public void setAmountPayable(Float amountPayable) {
        this.amountPayable = amountPayable;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
