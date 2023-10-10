package com.xxxx.supermarket.controller;

import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.entity.Customer;
import com.xxxx.supermarket.entity.Provider;
import com.xxxx.supermarket.service.ProviderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("provider")
public class ProviderController extends BaseController {


    @Resource
    private ProviderService providerService;


    @RequestMapping("allProvider")
    @ResponseBody
    public List<Provider> allProvider(){
        return providerService.allProvider();
    }
}
