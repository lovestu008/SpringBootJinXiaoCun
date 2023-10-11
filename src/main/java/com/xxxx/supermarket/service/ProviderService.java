package com.xxxx.supermarket.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.dao.ProviderMapper;
import com.xxxx.supermarket.entity.Provider;
import com.xxxx.supermarket.query.QueryProvider;
import com.xxxx.supermarket.utils.AssertUtil;
import com.xxxx.supermarket.utils.PhoneUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProviderService extends BaseService<Provider,Integer> {

    @Resource
    private ProviderMapper providerMapper;

    public Map<String,Object> queryByParams(QueryProvider queryProvider){
        Map<String,Object> map = new HashMap<>();
        // 开启分页            当前是第几页                 每页显示几个
        PageHelper.startPage(queryProvider.getPage(), queryProvider.getLimit());
        /*//得到对应的分页对象
        PageInfo<SaleChance> pageInfo = new PageInfo<>(saleChanceMapper.selectByParams(saleChanceQuery));*/
        PageInfo<Provider> pageInfo= new PageInfo<>(providerMapper.selectByParams(queryProvider));
        // 设置map对象
        map.put("code","0");
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        // 设置分页好的列表
        map.put("data",pageInfo.getList());
        return map;
    }

    /**
     * 添加供货商
     * @param provider
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addProvider(Provider provider){

        /* 1.参数校验*/
        checkSaleChanceParams(provider.getAddress(), provider.getContact(), provider.getName(), provider.getNumber());
        /*2.设置相关参数默认值*/
        // isDel是否有效  （0=无效，1=有效）
        provider.setIsDel(0);
        /* 3.判断受影响行数*/
        AssertUtil.isTrue(providerMapper.insertSelective(provider)!=1,"添加供应商失败");
    }

    private void checkSaleChanceParams(String address, String contact, String name, String number) {
        /* 参数校验码 */
        // 供应商地址不能为空
        AssertUtil.isTrue(StringUtils.isBlank(address),"供应商地址不能为空");
        // 联系人不能为空
        AssertUtil.isTrue(StringUtils.isBlank(contact),"联系人不能为空");
        // 供应商名称不能为空
        AssertUtil.isTrue(StringUtils.isBlank(name),"供应商名称不能为空");
        // 电话号码不能为空，格式正确
        AssertUtil.isTrue(StringUtils.isBlank(number),"电话号码不能为空");
        AssertUtil.isTrue(!PhoneUtil.isMobile(number),"电话号码格式不正确");
    }

    /**
     * 更新供应商
     * @param provider
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateProvider(Provider provider){
        /* 1. 参数校验  */
        //  供应商ID  非空，数据库中对应的记录存在
        AssertUtil.isTrue(null == provider.getId(), "待更新记录不存在！");
        // 通过主键查询对象
        Provider temp = providerMapper.selectByPrimaryKey(provider.getId());
        // 判断数据库中对应的记录存在
        AssertUtil.isTrue(temp == null,"待更新记录不存在！");
        // 参数校验
        checkSaleChanceParams(provider.getAddress(), provider.getContact(), provider.getName(), provider.getNumber());
        /*2.设置相关参数默认值*/
        AssertUtil.isTrue(null==temp.getIsDel(),"数据状态不能为空 （0=无效，1=有效）");
        /* 3.判断受影响行数*/
        AssertUtil.isTrue(providerMapper.updateByPrimaryKeySelective(provider) != 1, "更新供应商失败！");
    }
    /**
     * 删除供应商
     * @param ids
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteBeach(Integer[] ids){
        //判断Id是否为空
        AssertUtil.isTrue(null==ids,"待删除记录不存在");
        //判断受影响行数
        AssertUtil.isTrue(providerMapper.deleteBatch(ids)!=ids.length,"供应商删除失败");
    }
}
