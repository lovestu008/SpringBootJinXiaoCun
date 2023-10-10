package com.xxxx.supermarket.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode
@ApiModel(value = "CustomerReturnList对象",description = "客户退货表单")
public class CustomerReturnList implements Serializable {
    private Integer id;//主键

    private Float amountPaid=0.0F;//实付金额

    private Float amountPayable=0.0F;//应付金额

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date customerReturnDate = new Date();//退货日期

    private String customerReturnNumber;//退货单号

    private String remarks;//备注

    private Integer state;//交易状态

    private Integer userId;//操作用户

    private Integer customerId;//客户id

    private String userName;//操作员名

    private String customerName;//客户名

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getCustomerReturnDate() {
        return customerReturnDate;
    }

    public void setCustomerReturnDate(Date customerReturnDate) {
        this.customerReturnDate = customerReturnDate;
    }

    public String getCustomerReturnNumber() {
        return customerReturnNumber;
    }

    public void setCustomerReturnNumber(String customerReturnNumber) {
        this.customerReturnNumber = customerReturnNumber == null ? null : customerReturnNumber.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}