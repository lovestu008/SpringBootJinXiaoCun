package com.xxxx.supermarket.controller;

import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.base.ResultInfo;
import com.xxxx.supermarket.entity.SaleList;
import com.xxxx.supermarket.query.SaleQuery;
import com.xxxx.supermarket.service.SaleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping("sale")
@Controller
public class SaleController extends BaseController {

    @Resource
    private SaleService saleService;

    /**
     * 销售主页
     *
     * @param request
     * @return
     */
    @RequestMapping("index")
    public String index(HttpServletRequest request) {

        return "sale/sale";
    }

    /**
     * 分⻚查询销售单
     *
     * @param query
     * @return
     */

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> querySaleListParams(SaleQuery query) {
        return saleService.querySaleListParams(query);
    }

    /**
     * 转发添加或者修改页面
     *
     * @return
     */
    @RequestMapping("addOrUpdateSalePage")
    public String addOrUpdateSalePage(Integer id, HttpServletRequest request) {
        //判断saleChanceId是否为空
        if (id != null) {
            //通过Id查询营销机会数据
            SaleList saleList = saleService.selectByPrimaryKey(id);
            //将数据设置到请求域中
            request.setAttribute("saleList", saleList);
        }
        return "sale/add_update";
    }


    /**
     * 删除销售单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("delete")
    public ResultInfo deleteSaleList(Integer[] ids) {
        saleService.deleteSale(ids);
        return success("销售单删除成功");
    }

    /**
     * 增加销售单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("add")
    public ResultInfo addSaleList(SaleList saleList) {
        saleService.addSale(saleList);
        return success("销售单添加成功");
    }

    /**
     * 修改销售单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("update")
    public ResultInfo updateSaleList(SaleList saleList) {
        saleService.updateSale(saleList);
        return success("销售单修改成功");
    }


}
