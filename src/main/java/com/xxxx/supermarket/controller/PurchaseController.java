package com.xxxx.supermarket.controller;

import com.xxxx.supermarket.aspect.SupLog;
import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.base.ResultInfo;
import com.xxxx.supermarket.entity.Purchase;
import com.xxxx.supermarket.querys.PurchaseQuery;
import com.xxxx.supermarket.service.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("purchase")
@Slf4j
@SupLog(type = "商品进货")
public class PurchaseController extends BaseController {
    @Resource
    private PurchaseService purchaseService;

    @RequestMapping("index")
    public String index(){
        return "purchase/purchase";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> selectByParams(PurchaseQuery purchaseQuery){
        System.out.println(purchaseQuery);
        if (purchaseQuery.getProvider()!=null){
            if (purchaseQuery.getProvider().equals("请选择供应商")) purchaseQuery.setProvider(null);
            if (purchaseQuery.getGoodsName().equals("请选择商品")) purchaseQuery.setGoodsName(null);
        }
        System.out.println(purchaseQuery);
        return purchaseService.queryByParamsForTable(purchaseQuery);
    }

    @RequestMapping("selectAllProvider")
    @ResponseBody
    public List<Map<String,Object>> selectAllProvider(){
        return purchaseService.selectAllProvider();
    }
    @RequestMapping("selectAllGoodsName")
    @ResponseBody
    public List<Map<String,Object>> selectAllGoodsName(){
        return purchaseService.selectAllGoodsName();
    }
    @PostMapping("add")
    @ResponseBody
    @SupLog(content = "添加进货信息")
    public ResultInfo addPurchase(Purchase purchase){
        purchaseService.addPurchase(purchase);
        return success("进货信息添加成功");
    }
    @RequestMapping("toAddOrUpdatePurchasePage")
    public String toAddOrUpdateRolePage(){
        return "purchase/add_update";
    }
    @PostMapping("delete")
    @ResponseBody
    @SupLog(content = "删除进货信息")
    public ResultInfo deletePurchase(Integer id){
        purchaseService.deletePurchase(id);
        return success("进货信息删除成功");
    }

}
