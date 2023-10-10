package com.xxxx.supermarket.controller;


import com.xxxx.supermarket.query.SaleListGoodsQuery;
import com.xxxx.supermarket.service.SaleListGoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@RequestMapping("saleListGoods")
@Controller
public class SaleListGoodsController {



    @Resource
    private SaleListGoodsService saleListGoodsService;


    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> saleListGoodsList(SaleListGoodsQuery saleListGoodsQuery){
        return saleListGoodsService.saleListGoodsList(saleListGoodsQuery);
    }
}
