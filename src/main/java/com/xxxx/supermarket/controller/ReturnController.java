package com.xxxx.supermarket.controller;

import com.xxxx.supermarket.annotation.RequiredPermission;
import com.xxxx.supermarket.aspect.SupLog;
import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.dao.ReturnMapper;
import com.xxxx.supermarket.entity.Return;
import com.xxxx.supermarket.querys.PurchaseQuery;
import com.xxxx.supermarket.querys.ReturnQuery;
import com.xxxx.supermarket.service.ReturnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RequestMapping("return")
@Controller
public class ReturnController extends BaseController {
    @Resource
    private ReturnService returnService;
    @RequiredPermission(code = "2010")
    @RequestMapping("index")
    public String index(){
        return "return/return";
    }

    @RequiredPermission(code = "2010")
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> selectByParams(ReturnQuery returnQuery){
        if (returnQuery.getProvider()!=null){
            if (returnQuery.getProvider().equals("请选择供应商")) returnQuery.setProvider(null);
            if (returnQuery.getGoodsName().equals("请选择商品")) returnQuery.setGoodsName(null);
        }
        System.out.println(returnQuery);
        return returnService.queryByParamsForTable(returnQuery);
    }
    @RequiredPermission(code = "2010")
    @RequestMapping("selectAllProvider")
    @ResponseBody
    public List<Map<String,Object>> selectAllProvider(){
        return returnService.selectAllProvider();
    }

    @RequiredPermission(code = "2010")
    @RequestMapping("selectAllGoodsName")
    @ResponseBody
    public List<Map<String,Object>> selectAllGoodsName(){
        return returnService.selectAllGoodsName();
    }


}
