package com.xxxx.supermarket.service;

import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.dao.PurchaseMapper;
import com.xxxx.supermarket.dao.ReturnMapper;
import com.xxxx.supermarket.entity.Purchase;
import com.xxxx.supermarket.entity.Return;
import com.xxxx.supermarket.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PurchaseService extends BaseService<Purchase,Integer> {
    @Resource
    private PurchaseMapper purchaseMapper;
    @Resource
    private ReturnMapper returnMapper;

    public List<Map<String,Object>> selectAllProvider(){
        List<Map<String, Object>> maps = purchaseMapper.selectAllProvider();
        System.out.println(maps);
        return purchaseMapper.selectAllProvider();
    }

    public List<Map<String, Object>> selectAllGoodsName() {

        return purchaseMapper.selectAllGoodsName();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addPurchase(Purchase purchase){
        AssertUtil.isTrue(StringUtils.isBlank(purchase.getGoodsName()),"商品名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(purchase.getGoodsName()),"供应商不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(purchase.getGoodsName()),"不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(purchase.getGoodsName()),"进货价格不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(purchase.getGoodsName()),"进货数量不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(purchase.getGoodsName()),"进货总价格不能为空");
        //设置默认值
        purchase.setIsValid(1);
        purchase.setInpTime(new Date());
        //受影响行数判断
        AssertUtil.isTrue(purchaseMapper.insertSelective(purchase)!=1,"添加进货信息失败，请重试");


    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void deletePurchase(Integer id) {
        AssertUtil.isTrue(id==null,"待删除记录不存在");
        Purchase temp=purchaseMapper.selectByPrimaryKey(id);
        AssertUtil.isTrue(temp==null,"待删除记录不存在");
        temp.setIsValid(0);
        AssertUtil.isTrue(purchaseMapper.updateByPrimaryKeySelective(temp)!=1,"删除记录失败，请重试！");




    }
}
