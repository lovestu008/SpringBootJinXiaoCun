package com.xxxx.supermarket.controller;


import com.xxxx.supermarket.annotation.RequiredPermission;
import com.xxxx.supermarket.annotation.SupLog;
import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.base.ResultInfo;
import com.xxxx.supermarket.entity.Goods;
import com.xxxx.supermarket.model.GoodsModel;
import com.xxxx.supermarket.query.GoodsQuery;
import com.xxxx.supermarket.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("goods")
@Slf4j
@SupLog(type = "商品管理")
public class GoodsController extends BaseController {

    @Resource
    private GoodsService goodsService;

    /**
     * 进入商品管理页面
     *
     * @return
     */
    @RequiredPermission(code = "102010")
    @RequestMapping("index")
    public String index(){
        return "goods/goodsManager";
    }



    /**
     * 多条件商品数据分页查询
     */
    @RequiredPermission(code = "102010")
    @RequestMapping ("goodsList")
    @ResponseBody
    public Map<String, Object> selectByPrimaryKey(GoodsQuery goodsQuery){
        return goodsService.queryGoodsByParams(goodsQuery);
    }
    /**
     * 添加商品
     *
     * @param goodsModel
     * @return
     */
    @RequiredPermission(code = "102010")
    @PostMapping("add")
    @ResponseBody
    @SupLog(content = "商品数据添加操作")
    public ResultInfo addGoods(GoodsModel goodsModel){
        goodsService.addGoods(goodsModel);
        return success("商品添加成功！");
    }
    /**
     * 更新商品数据
     *
     * @param goodsModel
     * @return
     */
    @RequiredPermission(code = "102010")
    @PostMapping("update")
    @ResponseBody
    @SupLog(content = "商品数据修改操作")
    public ResultInfo updateGoods(GoodsModel goodsModel){
        //调用service层方法
        goodsService.updateGoods(goodsModel);
        return success("商品数据修改成功！");
    }
    /**
     *删除商品数据(单条删除)
     *
     * @param id
     * @return
     */
    @RequiredPermission(code = "102010")
    @PostMapping("delete")
    @ResponseBody
    @SupLog(content = "商品数据删除操作")
    public ResultInfo deleteGoods(Integer id){
        //调用service层方法
        goodsService.deleteGoods(id);
        return success("删除成功！");
    }

    /**
     * 删除商品数据(批量)
     * @param ids
     * @return
     */
    @ResponseBody
    @PostMapping("allDelete")
    public ResultInfo deleteAllGoods(Integer[] ids){
        goodsService.deleteAllGoods(ids);
        return success("删除成功！");
    }

    /**
     * 进入 添加或修改 商品信息的页面
     * @return
     */
    @RequiredPermission(code = "102010")
    @RequestMapping("toAddOrUpdateGoodsPage")
    public String toAddOrUpdateGoodsPage(Integer id,Integer typeId,HttpServletRequest request){
        //如果id不为空，则表示是修改操作，通过id查询角色记录，设置到请求域中
        if (null != id){
            Goods goods =goodsService.selectByPrimaryKey(id);
            request.setAttribute("goods",goods);
        }
        return "goods/add_update";
    }

}
