package com.xxxx.supermarket.service.impl;

import com.xxxx.supermarket.entity.Customer;
import com.xxxx.supermarket.mapper.CustomerMapper;
import com.xxxx.supermarket.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 
 * @since 2023-03-04
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}
