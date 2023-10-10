package com.xxxx.supermarket.service;

import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.dao.PurchaseMapper;
import com.xxxx.supermarket.dao.ReturnMapper;
import com.xxxx.supermarket.entity.Purchase;
import com.xxxx.supermarket.entity.Return;
import com.xxxx.supermarket.model.InpRetGoodsList;
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
     * 更新进货单
     * @param purchase
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePurchase(Purchase purchase) {
        System.out.println("商品名字："+purchase.getGoodsName());
        AssertUtil.isTrue(StringUtils.isBlank(purchase.getGoodsName()),"商品名称不能为空");
        AssertUtil.isTrue(purchase.getInpNum()<=0,"进货数量不能小于零");
        AssertUtil.isTrue(purchase.getInpPrice()<=0,"进货价格不能小于零");
        //设置默认值
        purchase.setInpTime(new Date());
        String provider =purchaseMapper.selectProviderByGoodsNameFromGoods(purchase.getGoodsName());
        purchase.setAllInpPrice(PriceUtil.priceProduct(purchase.getInpPrice(),purchase.getInpNum()));
        purchase.setProvider(provider);
        //受影响行数判断
        AssertUtil.isTrue(purchaseMapper.updateByPrimaryKeySelective(purchase)!=1,"修改进货信息失败，请重试");

    }

    public List<Map<String,Object>> selectAllGoodsNameById(){
        return purchaseMapper.selectAllGoodsNameById();
    }

    /**
     * 退货页面
     * @param id
     * @return
     */
    public InpRetGoodsList setIntRetGoodsList(Integer id) {
        //查询商品信息
        Purchase purchase=purchaseMapper.selectByPrimaryKey(id);
        //库存数量
        Integer goodsNum =purchaseMapper.selectNumByGoodsName(purchase.getGoodsName());

        InpRetGoodsList inpRetGoodsList=new InpRetGoodsList();
        inpRetGoodsList.setPurchaseId(purchase.getId());
        //设置退货商品名称
        inpRetGoodsList.setGoodsName(purchase.getGoodsName());
        //设置供应商
        inpRetGoodsList.setProvider(purchase.getProvider());
        //设置库存数量
        inpRetGoodsList.setGoodsNum(goodsNum);
        return inpRetGoodsList;
    }

    /**
     * 退货
     * @param inpRetGoodsList
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void returnPurchase(InpRetGoodsList inpRetGoodsList) {
        AssertUtil.isTrue(inpRetGoodsList.getPurchaseId()==null,"待退货记录不存在");
        Purchase temp=purchaseMapper.selectByPrimaryKey(inpRetGoodsList.getPurchaseId());
        AssertUtil.isTrue(temp==null,"待退货记录不存在");

        Return r=new Return();
        //退货商品名称
        r.setGoodsName(temp.getGoodsName());
        //商品供应商
        r.setProvider(temp.getProvider());
        //操作人
        r.setOperatePerson(temp.getOperatePerson());
        //退货数量
        r.setRetNum(inpRetGoodsList.getRetNum());
        //退货总价
        r.setAllRetPrice(PriceUtil.priceProduct(temp.getInpPrice(),inpRetGoodsList.getRetNum()));
        System.out.println("退货总价格"+r.getAllRetPrice());
        //退货备注
        r.setRemark(inpRetGoodsList.getRemark());
        //退货时间
        r.setRetTime(new Date());
        //退货信息可用
        r.setIsValid(1);
        AssertUtil.isTrue(returnMapper.insertSelective(r)!=1,"退货失败，请检查信息并重试！");


        //商品库存=原库存-退货数量（修改goods表）
        Integer goodsNum = purchaseMapper.selectNumByGoodsName(r.getGoodsName())-r.getRetNum();
        AssertUtil.isTrue(purchaseMapper.updateGoodsNum(goodsNum,inpRetGoodsList.getGoodsName())!=1,"库存数量更新失败");

    }
    //查询当月进货分布
    public List<Purchase> selectByTime() {
        return purchaseMapper.selectByTime();
    }
}
