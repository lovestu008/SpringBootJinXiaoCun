package com.xxxx.supermarket.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.dao.CustomerReturnListGoodsMapper;
import com.xxxx.supermarket.dao.CustomerReturnListMapper;
import com.xxxx.supermarket.entity.CustomerReturnList;
import com.xxxx.supermarket.entity.CustomerReturnListGoods;
import com.xxxx.supermarket.entity.Goods;
import com.xxxx.supermarket.entity.SaleList;
import com.xxxx.supermarket.query.CustomerReturnListQuery;
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
public class CustomerReturnListService extends BaseService<CustomerReturnList, Integer> {


    @Resource
    private CustomerReturnListMapper customerReturnListMapper;
    @Resource
    private CustomerReturnListGoodsMapper customerReturnListGoodsMapper;
    @Resource
    private GoodsService goodsService;

    /**
     * 保存退货单
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addCustomerReturnSaleList(CustomerReturnList cRL, List<CustomerReturnListGoods> customerReturnListGoods) {
        AssertUtil.isTrue(customerReturnListMapper.insertSelective(cRL) < 1, "退货单添加失败");
        CustomerReturnList temp = customerReturnListMapper.queryCustomerReturnListByCustomerReturnNumber(cRL.getCustomerReturnNumber());

        customerReturnListGoods.forEach(cRLG -> {
            //将临时单内的编号赋予每一个退货商品对象
            cRLG.setCustomerReturnListId(temp.getId());
            //获取crlg的goodsid来查询获取商品
            Goods goods = goodsService.getGoodsById(cRLG.getGoodsId());
            //设置库存为原库存加上退货单内的数量
            goods.setInventoryQuantity(goods.getInventoryQuantity() + cRLG.getNum());
            //
            goods.setState(2);
            //通过id来更改数据库内的goods数据
            AssertUtil.isTrue((goodsService.updateByGoods(goods)) < 1, "记录添加失败!");
            //向crlg表内添加数据
            AssertUtil.isTrue(customerReturnListGoodsMapper.insertSelective(cRLG)<1,"记录添加失败");
        });
    }

    /**
     * 获取下一个退货单号
     * @return
     */
    public Object getNextCustomerReturnNumber() {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("XT");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            String saleNumber = customerReturnListGoodsMapper.getNextSaleNumber();
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
     * 根据参数查找对应的退货单
     * @param query
     * @return
     */
    public Map<String, Object> queryCustomerReturnListParams(CustomerReturnListQuery query) {
        Map<String, Object> map = new HashMap<>();
        //开启分页
        PageHelper.startPage(query.getPage(), query.getLimit());
        //得到对应分页对象
        PageInfo<CustomerReturnList> pageInfo = new PageInfo<>(customerReturnListMapper.querySaleListByParams(query));
        //设置map对象
        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());
        //将分页好的列表存入map
        map.put("data", pageInfo.getList());
        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Integer id) {
        AssertUtil.isTrue(null==id,"id为空,请重试");
        //若有外键关联,则提示无法删除
        AssertUtil.isTrue(customerReturnListGoodsMapper.queryCustomerReturnListGoodsByCustomerReturnListId(id).size()>0,"该订单仍有内容,无法删除");
        AssertUtil.isTrue(customerReturnListMapper.deleteById(id)<1,"删除失败");
    }
}
