package com.xxxx.supermarket.controller;


import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.base.ResultInfo;
import com.xxxx.supermarket.entity.Goods;
import com.xxxx.supermarket.model.GoodsModel;
import com.xxxx.supermarket.query.GoodsQuery;
import com.xxxx.supermarket.service.GoodsService;
import com.xxxx.supermarket.service.GoodsTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("goods")
public class GoodsController extends BaseController {

    @Resource
    private GoodsService goodsService;
/*    @Resource
    private GoodsTypeService goodsTypeService;*/
    /**
     * 进入商品管理页面
     *
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "goods/goodsManager";
    }



    /**
     * 多条件商品数据分页查询
     */
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
    @PostMapping("add")
    @ResponseBody
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
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateGoods(GoodsModel goodsModel){
        //调用service层方法
        goodsService.updateGoods(goodsModel);
        return success("商品数据修改成功！");
    }
    /**
     *删除商品数据
     *
     * @param id
     * @return
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteGoods(Integer id){
        //调用service层方法
        goodsService.deleteGoods(id);
        return success("删除成功！");
    }



    /**
     * 进入 添加或修改 商品信息的页面
     * @return
     */
    @RequestMapping("toAddOrUpdateGoodsPage")
    public String toAddOrUpdateGoodsPage(Integer id,Integer typeId,HttpServletRequest request){
        //如果id不为空，则表示是修改操作，通过id查询角色记录，设置到请求域中
        if (null != id){
            Goods goods =goodsService.selectByPrimaryKey(id);
            request.setAttribute("goods",goods);
        }else if(null != typeId){  //如果typeId不为空，则表示是添加操作

        }

        return "goods/add_update";
    }

    /**
     *进入商品类别选择页
     * @param typeId
     * @param request
     * @return
     */
    @RequestMapping("toGoodsTypePage")
    public String toGoodsTypePage(Integer typeId, HttpServletRequest request) {
        if(null != typeId){
            request.setAttribute("typeId",typeId);
        }
        return "goodsType";
    }

}
