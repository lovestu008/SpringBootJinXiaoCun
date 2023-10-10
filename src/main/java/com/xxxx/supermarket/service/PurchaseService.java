package com.xxxx.supermarket.service;

import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.dao.PurchaseMapper;
import com.xxxx.supermarket.dao.ReturnMapper;
import com.xxxx.supermarket.entity.Purchase;
import com.xxxx.supermarket.entity.Return;
import com.xxxx.supermarket.utils.AssertUtil;
import com.xxxx.supermarket.utils.PriceUtil;
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
        //System.out.println(maps);
        return purchaseMapper.selectAllProvider();
    }

    public List<Map<String, Object>> selectAllGoodsName() {

        return purchaseMapper.selectAllGoodsName();
    }

    /**
     * 进货信息
     * @param purchase
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addPurchase(Purchase purchase){
        AssertUtil.isTrue(StringUtils.isBlank(purchase.getGoodsName()),"商品名称不能为空");
        AssertUtil.isTrue(null==purchase.getInpPrice(),"进货价格不能为空");
        AssertUtil.isTrue(null==purchase.getInpNum(),"进货数量不能为空");
        System.out.println(purchase.getGoodsName());
        //查找进货商品的id所匹配的供应商名字
        String provider =purchaseMapper.selectProviderByGoodsNameFromGoods(purchase.getGoodsName());
        System.out.println(purchase);
        AssertUtil.isTrue(StringUtils.isBlank(provider),"该商品没有供应商，请联系商品管理员 ");
        //设置默认值
        purchase.setProvider(provider);
        purchase.setAllInpPrice(PriceUtil.priceProduct(purchase.getInpPrice(),purchase.getInpNum()));
        purchase.setIsValid(1);
        purchase.setInpTime(new Date());
        System.out.println(purchase);
        //受影响行数判断
        AssertUtil.isTrue(purchaseMapper.insertSelective(purchase)!=1,"添加进货信息失败，请重试");

    }

    /**
     * 退货
     * @param id
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deletePurchase(Integer id) {
        AssertUtil.isTrue(id==null,"待删除记录不存在");
        Purchase temp=purchaseMapper.selectByPrimaryKey(id);
        AssertUtil.isTrue(temp==null,"待删除记录不存在");
        temp.setIsValid(0);
        AssertUtil.isTrue(purchaseMapper.updateByPrimaryKeySelective(temp)!=1,"删除记录失败，请重试！");

    }

    /**
     * 更新进货单
     * @param purchase
     */
    public void updatePurchase(Purchase purchase) {
        AssertUtil.isTrue(StringUtils.isBlank(purchase.getGoodsName()),"商品名称不能为空");
        AssertUtil.isTrue(purchase.getInpNum()<=0,"进货数量不能小于零");
        AssertUtil.isTrue(purchase.getInpPrice()<=0,"进货价格不能小于零");
        //设置默认值
        purchase.setInpTime(new Date());
        purchase.setAllInpPrice(PriceUtil.priceProduct(purchase.getInpPrice(),purchase.getInpNum()));
        //受影响行数判断
        AssertUtil.isTrue(purchaseMapper.updateByPrimaryKeySelective(purchase)!=1,"修改进货信息失败，请重试");

    }

    public List<Map<String,Object>> selectAllGoodsNameById(){
        return purchaseMapper.selectAllGoodsNameById();
    }
}
