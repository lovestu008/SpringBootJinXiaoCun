package com.xxxx.supermarket.controller;

import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.base.ResultInfo;
import com.xxxx.supermarket.querys.PurchaseQuery;
import com.xxxx.supermarket.service.PurchaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("purchase")
public class PurchaseController extends BaseController {
    @Resource
    private PurchaseService purchaseService;

    @RequestMapping("index")
    public String index(){
        return "purchase/purchase";
    }

    @GetMapping("list")
    @ResponseBody
    public Map<String,Object> selectByParams(PurchaseQuery purchaseQuery){
        return purchaseService.queryByParamsForTable(purchaseQuery);
    }

    @RequestMapping("selectAllProvider")
    @ResponseBody
    public List<Map<String,Object>> selectAllProvider(){
        return purchaseService.selectAllProvider();
    }
}
