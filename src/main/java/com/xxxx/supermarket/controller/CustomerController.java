package com.xxxx.supermarket.controller;

import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.query.QueryCustomer;
import com.xxxx.supermarket.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;


@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {
    @Resource
    private CustomerService customerService;

    /**
     * 多条件查询
     * @param queryCustomer
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryByParams(QueryCustomer queryCustomer){

        return customerService.queryByParams(queryCustomer);
    }

    /**
     *
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "customer/customer";
    }
}
