package com.xxxx.supermarket.model;

import com.xxxx.supermarket.entity.Goods;
import lombok.Data;

@Data
public class GoodsModel extends Goods {
    private Integer id;//编号
    private String code;//商品编码
    private String name;//商品名称
    private String model;//商品型号
    private String typeName;//商品类别
    private String unitName;//单位
    private Double purchasingPrice;//采购价格
    private Double sellingPrice;//出售价格
    private Integer minNum;//库存下限



    private String producer;//生产厂商
    private int isDel;
}
