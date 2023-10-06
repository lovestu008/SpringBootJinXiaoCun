package com.xxxx.supermarket.controller;


import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.base.ResultInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("common")
public class CommonController extends BaseController {
    @Resource
    private GoodsService goodsService;

    /**
     * 跳转至选择商品页面
     *
     * @return
     */
    @RequestMapping("toSelectGoodsPage")
    public String toSelectGoodsPage() {
        return "common/goods";
    }

    /**
     * 将选择的商品保存至作用域
     *
     * @return
     */
    @RequestMapping("queryGoodsByCode")
    @ResponseBody
    public ResultInfo queryGoodsByCode(Integer code, HttpServletRequest request) {
        Goods goods = goodsService.queryGoodsByCode(code);
        request.setAttribute("goods", goods);
        return success("返回选择的商品");
    }
}
