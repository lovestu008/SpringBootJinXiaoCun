package com.xxxx.supermarket.service;

import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.dao.ProviderMapper;
import com.xxxx.supermarket.entity.Customer;
import com.xxxx.supermarket.entity.Provider;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ProviderService extends BaseService<Provider,Integer> {

    @Resource
    private ProviderMapper providerMapper;
    public List<Provider> allProvider() {
        return providerMapper.allProvider();

    }
}
