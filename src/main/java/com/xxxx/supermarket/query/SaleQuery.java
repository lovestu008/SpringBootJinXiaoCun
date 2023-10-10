package com.xxxx.supermarket.query;

import com.xxxx.supermarket.base.BaseQuery;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class SaleQuery extends BaseQuery {
    private Integer customerId;//客户id
    private Float amountPaid;//实付金额
    private Float amountPayable;//应付金额
    private Date saleDate;//销售日期
    private String remarks;//备注
    private Integer state;//是否付款

    private String saleNumber;//订单号
    private String startDate;//开始日期
    private String endDate;//结束日期

    private String goodsName;//商品名
    private Integer typeId;//商品类型
    private List<Integer> typeIds;

    public Integer index;//页数

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
