package com.xxxx.supermarket.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.dao.SaleListGoodsMapper;
import com.xxxx.supermarket.dao.SaleListMapper;
import com.xxxx.supermarket.entity.SaleList;
import com.xxxx.supermarket.entity.SaleListGoods;
import com.xxxx.supermarket.model.SaleListGoodsModel;
import com.xxxx.supermarket.query.SaleQuery;
import com.xxxx.supermarket.utils.AssertUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaleService extends BaseService<SaleList, Integer> {

    @Resource
    SaleListMapper saleListMapper;

    @Resource
    SaleListGoodsMapper saleListGoodsMapper;


    /**
     * 多条件分⻚查询
     * @param query
     * @return
     */
    public Map<String, Object> querySaleListParams(SaleQuery query) {
        Map<String,Object> map = new HashMap<>();
        //开启分页
        PageHelper.startPage(query.getPage(), query.getLimit());
        //得到对应分页对象
        PageInfo<SaleList> pageInfo = new PageInfo<>(saleListMapper.selectByParams(query));
        //设置map对象
        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());
        //将分页好的列表存入map
        map.put("data", pageInfo.getList());
        return map;
    }

    /**
     * 删除销售单
     * @param ids
     */
    public void deleteSale(Integer[] ids) {
        //判断ID是否为空
        AssertUtil.isTrue(null == ids || ids.length < 1, "待删除的记录不存在");
        //执行删除(更新)操作,判断受影响的行数
        AssertUtil.isTrue(saleListGoodsMapper.deleteBatch(ids)!=ids.length,"销售单删除失败!");
    }

    public void addSale(SaleList saleList) {
    }

    public void updateSale(SaleList saleList) {
    }
    /**
     * 查询商品总销量前五
     */
    public List<SaleListGoodsModel> selectSaleListGoods(){
        return saleListGoodsMapper.selectSaleListGoods();
    }
}
