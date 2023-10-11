package com.xxxx.supermarket.service;

import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.dao.GoodsTypeMapper;
import com.xxxx.supermarket.entity.GoodsType;
import com.xxxx.supermarket.model.TreeGoodsModel;
import com.xxxx.supermarket.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class GoodsTypeService extends BaseService<GoodsType,Integer> {
    @Resource
    GoodsTypeMapper goodsTypeMapper;


    /**
     * 查询所有的商品类别
     * @param
     * @return
     */
    public List<TreeGoodsModel> queryAllGoodsTypes() {
        return  goodsTypeMapper.queryAllGoodsTypes();
    }

    /**
     * 加载商品类别管理页面的表格数据
     * @param
     * @return
     */
    public Map<String, Object> queryGoodsTypeList() {
        Map<String,Object> map = new HashMap<>();
        //查询所有资源列表
        List<GoodsType> goodsTypeList = goodsTypeMapper.queryGoodsTypeList();

        map.put("code",0);
        map.put("msg","");
        map.put("count",goodsTypeList.size());
        map.put("data",goodsTypeList);
        return map;
    }

    /**
     * 添加商品类别
     *     参数校验
     *         类别名称         非空，同一层级下唯一
     *     执行添加操作，返回受影响的行数
     * @param goodsType
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addGoodType(GoodsType goodsType){
        AssertUtil.isTrue(StringUtils.isBlank(goodsType.getName()),"商品类别名称不能为空！");
        AssertUtil.isTrue(goodsType.getId() == null,"商品父级id不能为空！");

        //添加新类别，把传入的 id,当做新类别的父级节点 pId
        //给添加的类别设置state层级为子类别 state=0
        goodsType.setState(0);
        AssertUtil.isTrue(goodsTypeMapper.insertGoodsType(goodsType) < 1,"商品类别添加失败！");

        //更新原本的子类别为父类别
        //根据id查表，查对应的类别的state是否是 0(子类别)
        GoodsType parent = goodsTypeMapper.selectByPrimaryKey(goodsType.getId());
        //如果添加位置的类别是 0(子类别) ，那么就把这个类别设置成父类别 1
        if (parent.getState() == 0){
            parent.setState(1);
        }
        AssertUtil.isTrue(goodsTypeMapper.updateByPrimaryKeySelective(parent) < 1,"商品类别添加失败！");

    }

    /**
     * 删除商品类别
     *     判断删除记录是否存在
     *              id          非空
     *     判断类别是否存在子类别
     *              如果有，就将id当做父id(pId)查询其下的子类别，如果有子类别，就不可删除
     *     执行删除操作，判断受影响的行数
     * @param id
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteGoodsType(Integer id) {
        AssertUtil.isTrue(null == id,"待删除记录不存在！");
        //判断类别是否存在子类别
        Integer count = goodsTypeMapper.queryGoodsTypeByParentId(id);
        //判断删除的子类别，其父类别的状态
        GoodsType num = goodsTypeMapper.selectByPrimaryKey(id);
        GoodsType num2 = goodsTypeMapper.selectByPrimaryKey(num.getpId());
        if(num2.getState()==1){
            //删除商品类别时，更新父节点状态
            AssertUtil.isTrue(goodsTypeMapper.updateByState(num2.getId()) <1 ,"删除失败！");
        }
            //如果存在则不能删除
            AssertUtil.isTrue(count >0,"该类别下存在子类，不可删除！");
            AssertUtil.isTrue(goodsTypeMapper.deleteByPrimaryKey(id)<1,"删除失败！");
    }

}


















