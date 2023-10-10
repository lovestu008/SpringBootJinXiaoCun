package com.xxxx.supermarket.controller;

import com.alibaba.fastjson.JSON;
import com.xxxx.supermarket.annotation.RequiredPermission;
import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.base.ResultInfo;
import com.xxxx.supermarket.entity.SaleList;
import com.xxxx.supermarket.entity.SaleListGoods;
import com.xxxx.supermarket.query.SaleQuery;
import com.xxxx.supermarket.service.GoodsService;
import com.xxxx.supermarket.service.SaleService;
import com.xxxx.supermarket.utils.AssertUtil;
import com.xxxx.supermarket.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequestMapping("/sale")
@Controller
public class SaleController extends BaseController {

    @Resource
    private SaleService saleService;

    @Resource
    private GoodsService goodsService;

    /**
     * 销售主页
     *
     * @param request
     * @return
     */
    @RequiredPermission(code = "30")
    @RequestMapping("index")
    public String index(HttpServletRequest request) {
        request.setAttribute("saleNumber",saleService.getNextSaleNumber());
        return "sale/sale";
    }
    /**
     * 单据查询主页
     */
    @RequiredPermission(code = "30")
    @RequestMapping("searchPage")
    public String searchPage(HttpServletRequest request){
        return "sale/sale_search";
    }

    /**
     * 分⻚查询销售单
     *
     * @param query
     * @return
     */

    @RequiredPermission(code = "30")
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> querySaleListParams(SaleQuery query) {
        Map<String,Object> map  =saleService.querySaleListParams(query);
        return map;
    }

    /**
     * 转发添加或者修改页面
     *
     * @return
     */
    @RequiredPermission(code = "30")
    @RequestMapping("addSalePage")
    public String addSalePage() {
        return "sale/add_update";
    }


    /**
     * 增加销售单
     *
     * @return
     */
    @RequiredPermission(code = "30")
    @ResponseBody
    @RequestMapping("save")
    public ResultInfo save(SaleList saleList,String goodsJson,HttpServletRequest request) {
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        saleList.setUserId(userId);
        AssertUtil.isTrue(goodsJson==null||goodsJson=="","无记录不能保存");
        AssertUtil.isTrue(saleList.getCustomerId()==null||saleList.getCustomerId()==0,"客户不能为空");
        List<SaleListGoods> saleListGoods = JSON.parseArray(goodsJson, SaleListGoods.class);
        saleService.saveSaleList(saleList,saleListGoods);
        return success("商品销售出库成功");
    }

    /**
     * 删除销售单
     * @return
     */
    @RequiredPermission(code = "30")
    @PostMapping ("delete")
    @ResponseBody
    public ResultInfo delete(Integer id){
        saleService.deleteById(id);

        return success("删除货单成功");
    }
}
