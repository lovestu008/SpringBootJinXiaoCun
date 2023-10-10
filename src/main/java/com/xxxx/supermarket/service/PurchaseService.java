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
import org.omg.CORBA.INTERNAL;
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
        //查找进货商品的id所匹配的供应商名字
        String provider =purchaseMapper.selectProviderByGoodsNameFromGoods(purchase.getGoodsName());
        AssertUtil.isTrue(StringUtils.isBlank(provider),"该商品没有供应商，请联系商品管理员 ");
        //设置默认值
        //设置供应商
        purchase.setProvider(provider);
        //设置进货总价
        purchase.setAllInpPrice(PriceUtil.priceProduct(purchase.getInpPrice(),purchase.getInpNum()));
        //设置可用属性值为1
        purchase.setIsValid(1);
        //设置操作时间
        purchase.setInpTime(new Date());
        //受影响行数判断

        //进货单添加一条数据
        AssertUtil.isTrue(purchaseMapper.insertSelective(purchase)!=1,"添加进货信息失败，请重试");


        //goods商品表中库存需要更新
        //库存等于原库存加现库存
        Integer goodsNum = purchaseMapper.selectNumByGoodsName(purchase.getGoodsName())+purchase.getInpNum();
        //判断上次进价和本次进价是否相同，如果不相同，讲上次进价设置为last_purchasing_price中，将本次进价设置为库存进价
        //获取上次进价
        Integer purchasingPrice = purchaseMapper.selectPurchasingPriceByGoodsName(purchase.getGoodsName());
        Integer lastPurchasingPrice = null;
        if (!purchasingPrice.equals(purchase.getInpPrice())){
            //将库存原有进货价格赋值给上次进货价格
            lastPurchasingPrice=purchasingPrice;
            //将进价赋值给库存进货价格
            purchasingPrice=purchase.getInpPrice();
        }
        //更新库存，进购价格和上次进购价格传入商品名称goodsName，商品现有库存goodsNum，进购价格purchasingPrice，上次进购价格lastPurchasingPrice
        AssertUtil.isTrue(purchaseMapper.updateGoodsNumAndLastPurchasingPrice(purchase.getGoodsName(),goodsNum,purchasingPrice,lastPurchasingPrice)!=1,"库存表进购信息更新失败，请及时检查！");

    }


    /**
     * 更新进货单
     * @param purchase
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePurchase(Purchase purchase) {
        AssertUtil.isTrue(StringUtils.isBlank(purchase.getGoodsName()),"商品名称不能为空");
        AssertUtil.isTrue(purchase.getInpNum()<=0,"进货数量不能小于零");
        AssertUtil.isTrue(purchase.getInpPrice()<=0,"进货价格不能小于零");
        Purchase temp =selectByPrimaryKey(purchase.getId());

        String provider =purchaseMapper.selectProviderByGoodsNameFromGoods(purchase.getGoodsName());
        //设置进货总价
        purchase.setAllInpPrice(PriceUtil.priceProduct(purchase.getInpPrice(),purchase.getInpNum()));
        //设置供应商
        purchase.setProvider(provider);
        //更新完成，受影响行数判断
        AssertUtil.isTrue(purchaseMapper.updateByPrimaryKeySelective(purchase)!=1,"修改进货信息失败，请重试");

        //goods商品表中库存需要更新
        //库存等于现库存+（修改后进货量purchase.getInpNum-修改前进货量temp.getInpNum）
        Integer goodsNum = purchaseMapper.selectNumByGoodsName(purchase.getGoodsName())+(purchase.getInpNum()-temp.getInpNum());
        //判断上次进价和本次进价是否相同，如果不相同，讲上次进价设置为last_purchasing_price中，将本次进价设置为库存进价
        //现进价=现进价+（修改后进价purchase.getInpPrice-修改前进价temp.getInpNum）
        //获取上次进价
        Integer purchasingPrice = purchaseMapper.selectPurchasingPriceByGoodsName(purchase.getGoodsName())+(purchase.getInpPrice()-temp.getInpPrice());
        if (!purchasingPrice.equals(purchase.getInpPrice())){
            //将进价赋值给库存进货价格
            purchasingPrice=purchase.getInpPrice();
        }
        //更新库存，进购价格和上次进购价格传入商品名称goodsName，商品现有库存goodsNum，进购价格purchasingPrice，上次进购价格lastPurchasingPrice
        AssertUtil.isTrue(purchaseMapper.updateGoodsNumAndPurchasingPrice(purchase.getGoodsName(),goodsNum,purchasingPrice)!=1,"库存表进购信息更新失败，请及时检查！");


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
}
