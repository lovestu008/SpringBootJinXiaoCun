package com.xxxx.supermarket.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.dao.SaleListGoodsMapper;
import com.xxxx.supermarket.dao.SaleListMapper;
import com.xxxx.supermarket.entity.Goods;
import com.xxxx.supermarket.entity.SaleList;
import com.xxxx.supermarket.entity.SaleListGoods;
import com.xxxx.supermarket.query.SaleQuery;
import com.xxxx.supermarket.utils.AssertUtil;
import com.xxxx.supermarket.utils.DateUtil;
import com.xxxx.supermarket.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaleService extends BaseService<SaleList, Integer> {

    @Resource
    private SaleListMapper saleListMapper;

    @Resource
    private SaleListGoodsMapper saleListGoodsMapper;
    @Resource
    private GoodsService goodsService;


    /**
     * 多条件分⻚查询
     *
     * @param query
     * @return
     */
    public Map<String, Object> querySaleListParams(SaleQuery query) {
        Map<String, Object> map = new HashMap<>();
        //开启分页
        PageHelper.startPage(query.getPage(), query.getLimit());
        //得到对应分页对象
        PageInfo<SaleList> pageInfo = new PageInfo<>(saleListMapper.querySaleListByParams(query));
        //设置map对象
        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());
        //将分页好的列表存入map
        map.put("data", pageInfo.getList());
        return map;
    }

    /**
     * 保存订单
     * @param saleList
     * @param saleListGoods
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveSaleList(SaleList saleList, List<SaleListGoods> saleListGoods) {
        AssertUtil.isTrue(saleListMapper.insertSelective(saleList) < 1, "记录添加失败!");
        //通过订单号获取临时订单对象
        SaleList temp = saleListMapper.querySaleListBySaleNumber(saleList.getSaleNumber());

        saleListGoods.forEach(slg -> {
            slg.setSaleListId(temp.getId());
            //获取slg的goodsid来查询获取商品
            Goods goods = goodsService.getGoodsById(slg.getGoodsId());
            //设置库存为原库存减去销售单内的数量
            goods.setInventoryQuantity(goods.getInventoryQuantity() - slg.getNum());
            //
            goods.setState(2);
            //通过id来更改数据库内的goods数据
            AssertUtil.isTrue((goodsService.updateByGoods(goods)) < 1, "记录添加失败!");
            //向slg表内添加数据
            AssertUtil.isTrue((saleListGoodsMapper.insertSelective(slg)) < 1, "记录添加失败!");
        });

    }

    /**
     * 获取下一个单号
     *
     * @return
     */
    public String getNextSaleNumber() {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("XS");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            String saleNumber = saleListMapper.getNextSaleNumber();
            if (null != saleNumber) {
                stringBuffer.append(StringUtil.formatCode(saleNumber));
            } else {
                stringBuffer.append("0001");
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 根据id删除订单
     * @param id
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Integer id) {
        AssertUtil.isTrue(null==id,"id为空,请重试");
        //若有外键关联,则提示无法删除
        AssertUtil.isTrue(saleListGoodsMapper.querySaleListGoodsBySaleListId(id).size()>0,"该订单仍有内容,无法删除");
        AssertUtil.isTrue(saleListMapper.deleteById(id)<1,"删除失败");
    }
}
