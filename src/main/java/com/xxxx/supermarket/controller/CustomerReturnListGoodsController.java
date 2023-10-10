package com.xxxx.supermarket.controller;

import com.xxxx.supermarket.annotation.RequiredPermission;
import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.entity.CustomerReturnListGoods;
import com.xxxx.supermarket.query.CustomerReturnListGoodsQuery;
import com.xxxx.supermarket.query.CustomerReturnListQuery;
import com.xxxx.supermarket.service.CustomerReturnListGoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("customerReturnListGoods")
public class CustomerReturnListGoodsController extends BaseController {

    @Resource
    private CustomerReturnListGoodsService customerReturnListGoodsService;

    @RequiredPermission(code = "3020")
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryCustomerReturnListGoodsByCustomerReturnListId(CustomerReturnListGoodsQuery customerReturnListGoodsQuery){

        return customerReturnListGoodsService.queryCustomerReturnListGoodsByCustomerReturnListId(customerReturnListGoodsQuery);
    }
}
