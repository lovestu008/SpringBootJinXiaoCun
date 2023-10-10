package com.xxxx.supermarket.service;

import com.github.pagehelper.IPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.dao.GoodsMapper;
import com.xxxx.supermarket.entity.Goods;
import com.xxxx.supermarket.query.GoodsQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class GoodsService extends BaseService<Goods, Integer> {

    @Resource
    private GoodsMapper goodsMapper;



    /**
     * 多条件 商品数据 分页查询
     *
     * @param goodsQuery
     * @return
     */
    public Map<String, Object> queryGoodsByParams(GoodsQuery goodsQuery) {
        Map<String, Object> map = new HashMap<>();

        // 开启分页
        PageHelper.startPage(goodsQuery.getPage(), goodsQuery.getLimit());
        // 得到对应分页对象
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsMapper.selectByParams(goodsQuery));

        //设置map对象
        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());//查到的数据的总数量
        // 设置分页好的列表
        map.put("data", pageInfo.getList());
        return map;
    }

    /**
     * 通过code查商品
     *
     * @param code
     * @return
     */
    public Goods queryGoodsByCode(Integer code) {
        return goodsMapper.queryGoodsByCode(code);
    }

    /**
     * 通过id查商品
     *
     * @param id
     * @return
     */
    public Goods getGoodsById(Integer id) {

        return goodsMapper.getGoodsById(id);
    }

    /**
     * 根据goods更改数据
     * @param goods
     * @return
     */@Transactional(propagation = Propagation.REQUIRED)
    public Integer updateByGoods(Goods goods) {
        return goodsMapper.updateByPrimaryKeySelective(goods);
    }
}
