package com.xxxx.supermarket.service.impl;

import com.xxxx.supermarket.entity.Goods;
import com.xxxx.supermarket.entity.Inport;
import com.xxxx.supermarket.mapper.GoodsMapper;
import com.xxxx.supermarket.mapper.InportMapper;
import com.xxxx.supermarket.service.InportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 
 * @since 2023-03-07
 */
@Service
public class InportServiceImpl extends ServiceImpl<InportMapper, Inport> implements InportService {


    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public boolean save(Inport entity) {

        Goods goods = goodsMapper.selectById(entity.getGoodsid());
        goods.setGquantity(goods.getGquantity()+entity.getNumber());
        goods.setDangerquantity(entity.getNumber()-goods.getDangerquantity());
        goodsMapper.updateById(goods);
        return super.save(entity);
    }

    @Override
    public boolean updateById(Inport entity) {
        Inport inport = baseMapper.selectById(entity.getId());
        Goods goods = goodsMapper.selectById(entity.getGoodsid());
        goods.setGquantity(goods.getGquantity()-inport.getNumber()+entity.getNumber());
        goodsMapper.updateById(goods);
        return super.updateById(entity);
    }


    @Override
    public boolean removeById(Serializable id) {
        Inport inport = baseMapper.selectById(id);
        Goods goods = goodsMapper.selectById(inport.getGoodsid());
        goods.setGquantity(goods.getGquantity()-inport.getNumber());
        goodsMapper.updateById(goods);
        return super.removeById(id);
    }
}
