package com.xxxx.supermarket.controller;

import com.xxxx.supermarket.annotation.RequiredPermission;
import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.entity.Customer;
import com.xxxx.supermarket.query.QueryCustomer;
import com.xxxx.supermarket.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
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
    @RequiredPermission(code = "101010")
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryByParams(QueryCustomer queryCustomer){

        return customerService.queryByParams(queryCustomer);
    }

    /**
     *
     * @return
     */
    @RequiredPermission(code = "101010")
    @RequestMapping("index")
    public String index(){
        return "customer/customer";
    }


    @RequiredPermission(code = "101010")
    @RequestMapping("allCustomers")
    @ResponseBody
    public List<Customer> allCustomers(){
        return customerService.allCustomers();
    }
}
