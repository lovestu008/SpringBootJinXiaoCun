package com.xxxx.supermarket.service;

import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.dao.GoodsTypeMapper;
import com.xxxx.supermarket.dto.TreeDto;
import com.xxxx.supermarket.entity.GoodsType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsTypeService extends BaseService<GoodsType,Integer> {

    @Resource
    private GoodsTypeMapper goodsTypeMapper;


    public List<TreeDto> queryAllGoodsTypes(Integer typeId) {
        List<TreeDto> treeDtos =goodsTypeMapper.queryAllGoodsTypes();
        if (null != typeId){
            for (TreeDto t:treeDtos
                 ) {
                if (t.getId().equals(typeId)){
                    //设置节点选中
                    t.setChecked(true);
                    break;
                }
            }
        }
        return treeDtos;
    }
}
