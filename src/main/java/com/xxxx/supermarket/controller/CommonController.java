package com.xxxx.supermarket.controller;


import com.xxxx.supermarket.annotation.RequiredPermission;
import com.xxxx.supermarket.annotation.SupLog;
import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.base.ResultInfo;
import com.xxxx.supermarket.entity.Goods;
import com.xxxx.supermarket.model.GoodsModels;
import com.xxxx.supermarket.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("common")
@Slf4j
@SupLog(type = "商品管理")
public class CommonController extends BaseController {
    @Resource
    private GoodsService goodsService;

    /**
     * 跳转至选择商品页面
     *
     * @return
     */
    @RequiredPermission(code = "30")
    @RequestMapping("toSelectGoodsPage")
    public String toSelectGoodsPage() {
        return "common/goods";
    }



    /**
     * 将选择的商品保存至作用域
     *
     * @return
     */
    @RequiredPermission(code = "30")
    @RequestMapping("queryGoodsByCode")
    @ResponseBody
    public ResultInfo queryGoodsByCode(Integer code, HttpServletRequest request) {
        Goods goods = goodsService.queryGoodsByCode(code);
        request.setAttribute("goods", goods);
        return success("返回选择的商品");
    }

    /**
     * 添加商品-商品信息添加页(单价、进货数量)
     * @param gid
     * @return
     */
    @RequiredPermission(code = "30")
    @RequestMapping("toAddGoodsInfoPage")
    @SupLog(content = "添加商品操作")
    public String toGoodsInfoPage(Integer gid, HttpServletRequest request){
        request.setAttribute("goods",goodsService.getGoodsById(gid));
        return "common/goods_add_update";
    }

    /**
     * 修改商品 - 商品信息修改页(单价、进货数量)
     * @param goodsModel
     * @return
     */
    @RequiredPermission(code = "30")
    @RequestMapping("toUpdateGoodsInfoPage")
    @SupLog(content = "修改商品操作")
    public String toUpdateGoodsInfoPage(GoodsModels goodsModel, HttpServletRequest request){
        //通过Id获取商品对象
        Goods goods = goodsService.getGoodsById(goodsModel.getId());
        //将商品对象的属性传入goodsModel
        goodsModel.setCode(goods.getCode());
        goodsModel.setModel(goods.getModel());
        goodsModel.setName(goods.getName());
        goodsModel.setUnit(goods.getUnitName());
        goodsModel.setTypeId(goods.getTypeId());
        goodsModel.setTypeName(goods.getTypeName());
        goodsModel.setLastPurchasingPrice(goods.getLastPurchasingPrice());
        goodsModel.setInventoryQuantity(goods.getInventoryQuantity());
        //设置入作用域
        request.setAttribute("goods",goodsModel);
        request.setAttribute("flag",1);
        return "common/goods_add_update";
    }
}
