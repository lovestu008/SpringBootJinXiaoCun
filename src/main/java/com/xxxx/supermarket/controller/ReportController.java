package com.xxxx.supermarket.controller;

import com.xxxx.supermarket.aspect.SupLog;
import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.entity.SaleListGoods;
import com.xxxx.supermarket.model.SaleListGoodsModel;
import com.xxxx.supermarket.query.SaleQuery;
import com.xxxx.supermarket.service.SaleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

@Controller
@RequestMapping("report")
public class ReportController extends BaseController {
    //进入统计报表界面
    @RequestMapping("index")
    public String index(){
        return "report/report";
    }

    @Resource
   private SaleService saleService;

    @RequestMapping("/statisticsSales")
    @ResponseBody
    public Map<String,Object> statisticsSales(){
        List<SaleListGoodsModel> listGoods = saleService.selectSaleListGoods();
        Map<String,Object> map = new HashMap<>();
        List<String> nameList = new ArrayList<>();
        List<Float> totalList = new ArrayList<>();
        if (listGoods != null && listGoods.size() > 0) {
            for (SaleListGoodsModel saleListGoods : listGoods) {
                nameList.add(saleListGoods.getCode() + saleListGoods.getName());
                totalList.add(saleListGoods.getAllTotal());
            }
        }
        map.put("data1",nameList);
        map.put("data2",totalList);
        return map;

    }

}
