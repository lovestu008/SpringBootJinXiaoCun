package com.xxxx.supermarket.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SaleList对象", description="销售单表")
public class SaleList implements Serializable {
    private Integer id;

    private Float amountPaid = 0.0F;//实付金额

    private Float amountPayable =0.0F;//应付金额

    private String remarks;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date saleDate = new Date();

    private String saleNumber;

    private Integer state;

    private Integer userId;

    private Integer customerId;

    private String userName;//操作员

    private String customerName;//客户名
}