package com.xxxx.supermarket.controller;

import com.xxxx.supermarket.aspect.SupLog;
import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.base.ResultInfo;
import com.xxxx.supermarket.entity.Purchase;
import com.xxxx.supermarket.model.InpRetGoodsList;
import com.xxxx.supermarket.querys.PurchaseQuery;
import com.xxxx.supermarket.service.PurchaseService;
import com.xxxx.supermarket.utils.AssertUtil;
import com.xxxx.supermarket.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("purchase")
@Slf4j
@SupLog(type = "商品进退货管理")
public class PurchaseController extends BaseController {
    @Resource
    private PurchaseService purchaseService;

    @RequestMapping("index")
    public String index(){
        return "purchase/purchase";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> selectByParams(PurchaseQuery purchaseQuery){
        System.out.println(purchaseQuery);
        return purchaseService.queryByParamsForTable(purchaseQuery);
    }

    @RequestMapping("selectAllProvider")
    @ResponseBody
    public List<Map<String,Object>> selectAllProvider(){
        System.out.println("----"+purchaseService.selectAllProvider());
        return purchaseService.selectAllProvider();
    }
    @RequestMapping("selectAllGoodsName")
    @ResponseBody
    public List<Map<String,Object>> selectAllGoodsName(){
        return purchaseService.selectAllGoodsName();
    }
    @PostMapping("add")
    @ResponseBody
    @SupLog(content = "添加进货信息")
    public ResultInfo addPurchase(Purchase purchase,HttpServletRequest request){
        //从cookie中获取用户姓名
        String userName= CookieUtil.getCookieValue(request,"userName");
        //设置营销机会的数据
        purchase.setOperatePerson(userName);
        purchaseService.addPurchase(purchase);
        return success("进货信息添加成功");
    }
    @PostMapping("update")
    @ResponseBody
    @SupLog(content = "修改进货信息")
    public ResultInfo updatePurchase(Purchase purchase){
        System.out.println(purchase.getGoodsName());
        System.out.println(purchase.getId());
        purchaseService.updatePurchase(purchase);
        return success("进货信息修改成功");
    }
    @RequestMapping("toAddPurchasePage")
    public String toAddPurchasePage(){
        return "purchase/add";
    }
    @RequestMapping("toUpdatePurchasePage")
    public String toUpdatePurchasePage(Integer id,HttpServletRequest request){
        AssertUtil.isTrue(null==id,"未选择，请重试");
        Purchase purchase =purchaseService.selectByPrimaryKey(id);
        request.setAttribute("purchase",purchase);
        return "purchase/update";
    }
    @PostMapping("return")
    @ResponseBody
    @SupLog(content = "添加退货信息")
    public ResultInfo returnPurchase(InpRetGoodsList inpRetGoodsList,HttpServletRequest request){
        //从cookie中获取用户姓名
        String userName= CookieUtil.getCookieValue(request,"userName");
        //设置营销机会的数据
        inpRetGoodsList.setOperatePerson(userName);
        purchaseService.returnPurchase(inpRetGoodsList);
        return success("进退货信息更新成功");
    }
    @RequestMapping("selectAllGoodsNameById")
    @ResponseBody
    public List<Map<String,Object>> selectAllGoodsNameById(){
        List list =  purchaseService.selectAllGoodsNameById();
        list.forEach(System.out::println);
        return list;
    }

    @RequestMapping("toReturnPurchasePage")
    public String toReturnPurchasePage(Integer id,HttpServletRequest request){
        AssertUtil.isTrue(null==id,"请选择要退货的信息！");
        //设置要返回的数据
        InpRetGoodsList inpRetGoodsList=purchaseService.setIntRetGoodsList(id);
        request.setAttribute("inpRetGoodsList",inpRetGoodsList);
        return "purchase/return_list";
    }

}
