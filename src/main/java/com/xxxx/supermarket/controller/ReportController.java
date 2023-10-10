package com.xxxx.supermarket.controller;

import com.xxxx.supermarket.annotation.RequiredPermission;
import com.xxxx.supermarket.aspect.SupLog;
import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.entity.Purchase;
import com.xxxx.supermarket.entity.SaleListGoods;
import com.xxxx.supermarket.model.SaleListGoodsModel;
import com.xxxx.supermarket.query.SaleQuery;
import com.xxxx.supermarket.service.PurchaseService;
import com.xxxx.supermarket.service.SaleService;
import com.xxxx.supermarket.utils.DataGridViewResult;
import com.xxxx.supermarket.utils.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("report")
public class ReportController extends BaseController {
    //进入统计报表界面
    @RequiredPermission(code = "6020")
    @RequestMapping("index")
    public String index(){
        return "report/report";
    }

    @Resource
   private SaleService saleService;
    @Resource
   private PurchaseService purchaseService;

//查询商品总销量前五
    @RequiredPermission(code = "6020")
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
//查询当月进货分布
    @RequiredPermission(code = "6020")
    @RequestMapping("/statisticsInGoods")
    @ResponseBody
    public Map<String,Object> statisticsInGoods(){
        List<Purchase> purchaseList = purchaseService.selectByTime();
        Map<String,Object> map = new HashMap<>();
        List<String> nameList = new ArrayList<>();
        List<Map<String,Object>> totalList = new ArrayList<>();
        if (purchaseList != null && purchaseList.size() > 0) {
            for (Purchase purchase : purchaseList) {
                nameList.add(purchase.getGoodsName());
                Map<String,Object> dataMap = new HashMap<>();
                dataMap.put("name",purchase.getGoodsName());
                dataMap.put("value",purchase.getInpNum());
                totalList.add(dataMap);
            }
        }
        map.put("data1",nameList);
        map.put("data2",totalList);
        return map;
    }

}
