package com.xxxx.supermarket.service;

import com.github.pagehelper.IPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.dao.GoodsMapper;
import com.xxxx.supermarket.entity.Goods;
import com.xxxx.supermarket.model.GoodsModel;
import com.xxxx.supermarket.query.GoodsQuery;
import com.xxxx.supermarket.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
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
     * @param goodsQuery
     * @return
     */
    public Map<String,Object> queryGoodsByParams(GoodsQuery goodsQuery){
        Map<String,Object> map = new HashMap<>();
        // 开启分页
        PageHelper.startPage(goodsQuery.getPage(), goodsQuery.getLimit());
        // 得到对应分页对象
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsMapper.selectByParams(goodsQuery));
        //设置map对象
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());//查到的数据的总数量
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

    /**
     * 添加商品
     *  参数校验
     *      商品名             非空，唯一
     *      采购价             非空，大于0
     *      销售价             非空，大于0
     *      库存下限            非空，大于0
     *  设置参数默认值
     *      设置是否是删除状态 0=有效 1=已删除
     *          is_del =0
     *  执行添加操作，判断受影响的行数
     * @param
     */
    @Transactional(propagation=Propagation.REQUIRED)
    public void addGoods(GoodsModel goodsModel){
        //校验参数
        CheckGoodsParams(goodsModel.getName(),goodsModel.getPurchasingPrice(), goodsModel.getSellingPrice(),goodsModel.getMinNum());
        //设置参数   0=有效 1=已删除
        goodsModel.setIsDel(0);
        //执行添加操作，判断受影响的行数
        AssertUtil.isTrue(goodsMapper.insertSelective(goodsModel) !=1,"商品添加失败！");
    }

    /**
     * 更新商品数据
     *   参数校验
     *      商品名             非空，唯一
     *      采购价             非空，大于0
     *      销售价             非空，大于0
     *      库存下限            非空，大于0
     *   判断原始数据是否存在
     *   执行更新操作，判断受影响的行数
     *
     * @param goodsModel
     */
    public void updateGoods(GoodsModel goodsModel) {
        //判断商品名 非空
        AssertUtil.isTrue(StringUtils.isBlank(goodsModel.getName()),"商品名不能为空！");
        //判断id和Name是否和数据库中的商品名相等，相等的话判断更新的id是否与数据库中的id是否相等
        Goods temp = goodsMapper.selectByGoodsName(goodsModel.getName());
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(goodsModel.getId())),"该商品已存在！请重试！");
        //采购价   非空，大于0
        AssertUtil.isTrue(null == goodsModel.getPurchasingPrice() || goodsModel.getPurchasingPrice() < 0 ,"采购价错误！请重试！");
        //销售价    非空，大于0
        AssertUtil.isTrue(null == goodsModel.getSellingPrice() || goodsModel.getSellingPrice()  < 0 ,"销售价错误！请重试！");
        //库存下限   非空，大于0
        AssertUtil.isTrue(null == goodsModel.getMinNum() || goodsModel.getMinNum() < 0 ,"库存下限错误！请重试！");

        //执行更新操作，判断受影响的行数
        AssertUtil.isTrue(goodsMapper.updateByPrimaryKeySelective(goodsModel) != 1 ,"商品数据更新失败！");
    }
    /**
     * 删除商品数据
     *    参数判断
     *        判断id是否为空
     *    执行删除操作，判断受影响的行数
     * @param id
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteGoods(Integer id){
        AssertUtil.isTrue(null == id,"待删除记录不存在！");
        AssertUtil.isTrue(goodsMapper.deleteGoods(id) != 1,"商品删除失败！");
    }
    /**
     * 参数判断方法
     *
     * @param name
     * @param purchasingPrice
     * @param sellingPrice
     * @param minNum
     */
    private void CheckGoodsParams(String name, Double purchasingPrice, Double sellingPrice, Integer minNum) {
        //商品名  非空,唯一
        AssertUtil.isTrue(StringUtils.isBlank(name),"商品名不能为空！");
        Goods temp = goodsMapper.selectByGoodsName(name);
        AssertUtil.isTrue(temp != null , "该商品已存在！请重试！");
        //采购价   非空，大于0
        AssertUtil.isTrue(null == purchasingPrice || purchasingPrice < 0 ,"采购价错误！请重试！");
        //销售价    非空，大于0
        AssertUtil.isTrue(null == sellingPrice || sellingPrice < 0 ,"销售价错误！请重试！");
        //库存下限   非空，大于0
        AssertUtil.isTrue(null == minNum || minNum < 0 ,"库存下限错误！请重试！");
    }
}
