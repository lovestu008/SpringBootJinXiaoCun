package com.xxxx.supermarket.entity;

import lombok.Data;

@Data
public class CustomerReturnListGoods {
    private Integer id;

    private String code;

    private String model;

    private String name;

    private Integer num;

    private Double price;

    private Double total;

    private String unit;

    private Integer customerReturnListId;

    private Integer typeId;

    private Integer goodsId;

}