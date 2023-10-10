package com.xxxx.supermarket.controller;

import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.base.ResultInfo;
import com.xxxx.supermarket.dto.TreeDto;
import com.xxxx.supermarket.service.GoodsTypeService;
import org.springframework.stereotype.Controller;
import com.xxxx.supermarket.entity.GoodsType;
import com.xxxx.supermarket.model.TreeGoodsModel;
import com.xxxx.supermarket.service.GoodsTypeService;
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
public class GoodsTypeController extends BaseController {
    @Resource
    private GoodsTypeService goodsTypeService;



    /**
     * 进入商品类别管理页面
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "goodsType/goods_type";
    }

    /**
     * 查找所有商品类别
     * @return
     */
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
    @ResponseBody
    @PostMapping("add")
    public ResultInfo addGoodType(GoodsType goodsType){
        goodsTypeService.addGoodType(goodsType);
        return success("添加成功！");
    }

    /**
     * 删除商品类别
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("delete")
    public ResultInfo deleteGoodsType(Integer id){
        goodsTypeService.deleteGoodsType(id);
        return success("删除成功！");
    }



    /**
     * 打开添加商品类别的页面
     * @return
     */
    @RequestMapping("addGoodsTypePage")
    public String addGoodsTypePage(Integer id, Model model){
        model.addAttribute("id",id);
        return "goodsType/add";
    }






}
