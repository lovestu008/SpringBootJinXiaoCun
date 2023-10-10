package com.xxxx.supermarket.controller;

import com.xxxx.supermarket.annotation.RequiredPermission;
import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.service.GoodsUnitService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("goodsUnit")
public class GoodsUnitController extends BaseController {

    @Resource
    GoodsUnitService goodsUnitService;

    /**
     * 查询所有的商品单位
     * @return
     */
    @RequiredPermission(code = "102020")
    @RequestMapping("allGoodsUnits")
    @ResponseBody
    public List<Map<String,Object>> allGoodsUnits(){
        return goodsUnitService.allGoodsUnits();
    }

}
