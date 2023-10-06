package com.xxxx.supermarket.controller;


import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.query.GoodsQuery;
import com.xxxx.supermarket.service.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("goods")
public class GoodsController extends BaseController {


    @Resource
    private GoodsService goodsService;


    /**
     * 进入商品管理页面
     *
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "goods/goodsManager";
    }




    /**
     * 多条件商品数据分页查询
     */
    @RequestMapping ("goodsList")
    @ResponseBody
    public Map<String, Object> selectByPrimaryKey(GoodsQuery goodsQuery){

        return goodsService.queryGoodsByParams(goodsQuery);
    }












}
