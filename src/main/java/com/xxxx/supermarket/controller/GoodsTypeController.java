package com.xxxx.supermarket.controller;

import com.xxxx.supermarket.annotation.RequiredPermission;
import com.xxxx.supermarket.annotation.SupLog;
import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.base.ResultInfo;
import com.xxxx.supermarket.model.TreeDto;
import com.xxxx.supermarket.entity.GoodsType;
import com.xxxx.supermarket.service.GoodsTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


import java.util.List;
import java.util.Map;



@Controller
@RequestMapping("goodsType")
@Slf4j
@SupLog(type = "商品管理")
public class GoodsTypeController extends BaseController {
    @Resource
    GoodsTypeService goodsTypeService;



    /**
     * 进入商品类别管理页面
     * @return
     */
    @RequiredPermission(code = "102020")
    @RequestMapping("index")
    public String index(){
        return "goodsType/goods_type";
    }
    /**
     * 查询所有的商品类别
     * @param
     */
    @RequiredPermission(code = "102020")
    @RequestMapping("queryAllGoodsTypes")
    @ResponseBody
    public List<TreeDto> queryAllGoodsTypes(Integer typeId){
        return goodsTypeService.queryAllGoodsTypes(typeId);
    }
    /**
     * 加载商品类别管理页面的表格数据
     * @param
     * @return
     */
    @RequiredPermission(code = "102020")
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryGoodsTypeList(){
        return goodsTypeService.queryGoodsTypeList();
    }



    /**
     * 添加商品类别
     * @param goodsType
     * @return
     */
    @RequiredPermission(code = "102020")
    @ResponseBody
    @PostMapping("add")
    @SupLog(content = "商品类别添加操作")
    public ResultInfo addGoodType(GoodsType goodsType){
        goodsTypeService.addGoodType(goodsType);
        return success("添加成功！");
    }
    /**
     * 删除商品类别
     * @param id
     * @return
     */
    @RequiredPermission(code = "102020")
    @ResponseBody
    @PostMapping("delete")
    @SupLog(content = "商品类别删除操作")
    public ResultInfo deleteGoodsType(Integer id){
        goodsTypeService.deleteGoodsType(id);
        return success("删除成功！");
    }



    /**
     * 打开添加商品类别的页面
     * @return
     */
    @RequiredPermission(code = "102020")
    @RequestMapping("addGoodsTypePage")
    public String addGoodsTypePage(Integer id, Model model){
        model.addAttribute("id",id);
        return "goodsType/add";
    }

}
