package com.xxxx.supermarket.controller;

import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.base.ResultInfo;
import com.xxxx.supermarket.dto.TreeDto;
import com.xxxx.supermarket.service.GoodsTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("goodsType")
public class GoodsTypeController extends BaseController {

    @Resource
    private GoodsTypeService goodsTypeService;


    /**
     * 查找所有商品类别
     * @return
     */
    @RequestMapping("queryAllGoodsTypes")
    @ResponseBody
    public List<TreeDto> queryAllGoodsTypes(Integer typeId){
         return goodsTypeService.queryAllGoodsTypes(typeId);
    }
}
