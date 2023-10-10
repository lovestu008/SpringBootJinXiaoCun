package com.xxxx.supermarket.controller;

import com.alibaba.fastjson.JSON;
import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.base.ResultInfo;
import com.xxxx.supermarket.entity.CustomerReturnList;
import com.xxxx.supermarket.entity.CustomerReturnListGoods;
import com.xxxx.supermarket.entity.SaleList;
import com.xxxx.supermarket.entity.SaleListGoods;
import com.xxxx.supermarket.query.CustomerReturnListQuery;
import com.xxxx.supermarket.query.SaleQuery;
import com.xxxx.supermarket.service.CustomerReturnListService;
import com.xxxx.supermarket.utils.AssertUtil;
import com.xxxx.supermarket.utils.LoginUserUtil;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("customerReturn")
public class CustomerReturnListController extends BaseController {

    @Resource
    private CustomerReturnListService customerReturnListService;

    /**
     * 进入退货单主页
     * @return
     */
    @RequestMapping("index")

    public String index(HttpServletRequest request){
        request.setAttribute("customerReturnNumber",customerReturnListService.getNextCustomerReturnNumber());
        return "customerReturn/customer_return";
    }

    /**
     * 进入退货单查询页面
     * @return
     */
    @RequestMapping("searchPage")
    public String search(){
        return "customerReturn/customer_return_search";
    }

    /**
     * 添加退货单
     * @param customerReturnList
     * @param goodsJson
     * @param request
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo addCustomerReturnSaleList(CustomerReturnList customerReturnList, String goodsJson, HttpServletRequest request){
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        customerReturnList.setUserId(userId);
        AssertUtil.isTrue(goodsJson==null||goodsJson=="","无记录不能保存");
        AssertUtil.isTrue(customerReturnList.getCustomerId()==null||customerReturnList.getCustomerId()==0,"客户不能为空");
        List<CustomerReturnListGoods> customerReturnListGoods = JSON.parseArray(goodsJson, CustomerReturnListGoods.class);
        customerReturnListService.addCustomerReturnSaleList(customerReturnList,customerReturnListGoods);
        return success("客户退货入库成功");
    }


    /**
     * 根据参数查找对应的退货单
     * @param query
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryCustomerReturnListParams(CustomerReturnListQuery query) {
        Map<String, Object> map = customerReturnListService.queryCustomerReturnListParams(query);
        return map;
    }
}
