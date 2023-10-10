package com.xxxx.supermarket.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.dao.CustomerMapper;
import com.xxxx.supermarket.entity.Customer;
import com.xxxx.supermarket.query.QueryCustomer;
import com.xxxx.supermarket.utils.AssertUtil;
import com.xxxx.supermarket.utils.PhoneUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService extends BaseService<Customer,Integer> {

    @Resource
    private CustomerMapper customerMapper;

    /**
     * 多条件查询
     * @param queryCustomer
     * @return
     */
    public Map<String,Object> queryByParams(QueryCustomer queryCustomer){
        Map<String,Object> map = new HashMap<>();
        // 开启分页            当前是第几页                 每页显示几个
        PageHelper.startPage(queryCustomer.getPage(), queryCustomer.getLimit());
        //得到对应的分页对象
        PageInfo<Customer> pageInfo= new PageInfo<>(customerMapper.selectByParams(queryCustomer));
        // 设置map对象
        map.put("code","0");
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        // 设置分页好的列表
        map.put("data",pageInfo.getList());
        return map;
    }

    /**
     * 添加超市客户
     * @param customer
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addCustomer(Customer customer){

        /* 1.参数校验*/
        checkSaleChanceParams(customer.getAddress(), customer.getContact(), customer.getName(), customer.getNumber());
        /*2.设置相关参数默认值*/
        // isValid是否有效  （0=有效，1=无效）
        customer.setIsDel(0);
        /* 3.判断受影响行数*/
        AssertUtil.isTrue(customerMapper.insertSelective(customer)!=1,"添加超市客户失败");
    }

    private void checkSaleChanceParams(String address, String contact, String name, String number) {
        /* 参数校验码 */
        // 超市地址不能为空
        AssertUtil.isTrue(StringUtils.isBlank(address),"超市地址不能为空");
        // 联系人不能为空
        AssertUtil.isTrue(StringUtils.isBlank(contact),"超市地址不能为空");
        // 超市名称不能为空
        AssertUtil.isTrue(StringUtils.isBlank(name),"超市名称不能为空");
        // 电话号码不能为空，格式正确
        AssertUtil.isTrue(StringUtils.isBlank(number),"电话号码不能为空");
        AssertUtil.isTrue(PhoneUtil.isMobile(number),"电话号码格式不正确");
    }

    /**
     * 更新超市客户
     * @param customer
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCustomer(Customer customer){
        /* 1. 参数校验  */
        //  超市客户ID  非空，数据库中对应的记录存在
        AssertUtil.isTrue(null == customer.getId(), "待更新记录不存在！");
        // 通过主键查询对象
        Customer temp = customerMapper.selectByPrimaryKey(customer.getId());
        // 判断数据库中对应的记录存在
        AssertUtil.isTrue(temp == null,"待更新记录不存在！");
        // 参数校验
        checkSaleChanceParams(customer.getAddress(), customer.getContact(), customer.getName(), customer.getNumber());
        /*2.设置相关参数默认值*/
        AssertUtil.isTrue(null==temp.getIsDel(),"数据状态不能为空 （0=无效，1=有效）");
        /* 3.判断受影响行数*/
        AssertUtil.isTrue(customerMapper.updateByPrimaryKeySelective(customer) != 1, "更新超市客户失败！");
    }

    /**
     * 删除营销机会
     * @param ids
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteBeach(Integer[] ids){
        //判断Id是否为空
        AssertUtil.isTrue(null==ids,"待删除记录不存在");
        //判断受影响行数
        AssertUtil.isTrue(customerMapper.deleteBatch(ids)!=ids.length,"超市客户删除失败");
    }

    public List<Customer> allCustomers() {
        return customerMapper.allCustomers();
    }
}
