package com.xxxx.supermarket.dao;

import com.xxxx.supermarket.base.BaseMapper;
import com.xxxx.supermarket.entity.GoodsType;
import com.xxxx.supermarket.model.TreeGoodsModel;


import java.util.List;


public interface GoodsTypeMapper extends BaseMapper<GoodsType,Integer> {
    /**
     * 查询所有的商品类别
     * @param
     * @return
     */
    public List<TreeGoodsModel> queryAllGoodsTypes();

    /**
     * 加载商品类别管理页面的表格数据
     * @return
     */
    public List<GoodsType> queryGoodsTypeList();

    /**
     * 把id当做父级id(pId)查询父级类别下是否存在子类
     * @param id
     * @return
     */
    public Integer queryGoodsTypeByParentId(Integer id);

    /**
     * 将id添加 当做pId 添加数据
     * @param goodsType
     * @return
     */
    Integer insertGoodsType(GoodsType goodsType);

    /**
     * 删除商品类别时，更新父节点状态
     * @param id
     * @return
     */
    public Integer updateByState(Integer id);
}