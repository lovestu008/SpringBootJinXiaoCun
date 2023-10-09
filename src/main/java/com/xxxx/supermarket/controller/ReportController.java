package com.xxxx.supermarket.controller;

import com.xxxx.supermarket.aspect.SupLog;
import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.entity.Purchase;
import com.xxxx.supermarket.entity.SaleListGoods;
import com.xxxx.supermarket.model.SaleListGoodsModel;
import com.xxxx.supermarket.query.SaleQuery;
import com.xxxx.supermarket.service.PurchaseService;
import com.xxxx.supermarket.service.SaleService;
import com.xxxx.supermarket.utils.DataGridViewResult;
import com.xxxx.supermarket.utils.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("report")
public class ReportController extends BaseController {
    //进入统计报表界面
    @RequestMapping("index")
    public String index(){
        return "report/report";
    }

    @Resource
   private SaleService saleService;
    @Resource
   private PurchaseService purchaseService;

//查询商品总销量前五
    @RequestMapping("/statisticsSales")
    @ResponseBody
    public Map<String,Object> statisticsSales(){
        List<SaleListGoodsModel> listGoods = saleService.selectSaleListGoods();
        Map<String,Object> map = new HashMap<>();
        List<String> nameList = new ArrayList<>();
        List<Float> totalList = new ArrayList<>();
        if (listGoods != null && listGoods.size() > 0) {
            for (SaleListGoodsModel saleListGoods : listGoods) {
                nameList.add(saleListGoods.getCode() + saleListGoods.getName());
                totalList.add(saleListGoods.getAllTotal());
            }
        }
        map.put("data1",nameList);
        map.put("data2",totalList);
        return map;
    }
//查询当月进货分布
    @RequestMapping("/statisticsInGoods")
    @ResponseBody
    public Map<String,Object> statisticsInGoods(){
        List<Purchase> purchaseList = purchaseService.selectByTime();
        Map<String,Object> map = new HashMap<>();
        List<String> nameList = new ArrayList<>();
        List<Map<String,Object>> totalList = new ArrayList<>();
        if (purchaseList != null && purchaseList.size() > 0) {
            for (Purchase purchase : purchaseList) {
                nameList.add(purchase.getGoodsName());
                Map<String,Object> dataMap = new HashMap<>();
                dataMap.put("name",purchase.getGoodsName());
                dataMap.put("value",purchase.getInpNum());
                totalList.add(dataMap);
            }
        }
        map.put("data1",nameList);
        map.put("data2",totalList);
        return map;
    }

    /*@SysLog("查询统计退货报表")
    @RequestMapping("/statisticsoutGoods")
    public DataGridViewResult statisticsoutGoods(){
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Outport> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1
                .select("sum( number ) as countnumbers,DATE_FORMAT( outputtime, '%Y-%m' ) AS counttime")
                .between("outputtime",DateUtils.stepMonth(5),new Date())
                .groupBy("counttime")
                .orderByAsc("counttime");
        List<Outport> outportsList = outportService.list(queryWrapper1);

        List<String> list3 = new ArrayList<>();
        List<Integer> list4 = new ArrayList<>();

        for (Outport outport : outportsList) {
            list3.add(outport.getCounttime());
            list4.add(outport.getCountnumbers());
        }
        map.put("data3",list3);
        map.put("data4",list4);
        return new DataGridViewResult(map);
    }

    @SysLog("查询盈利报表")
    @RequestMapping("/profitStatement")
    public DataGridViewResult profitStatement(){
        Map<String, Object> map = new HashMap<>();
        //时间
        List<String> list1 = new ArrayList<>();
        //销售利润
        List<Integer> list2 = new ArrayList<>();
        //实际利润
        List<Integer> list3 = new ArrayList<>();
        //退货
        List<Integer> list4 = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            QueryWrapper<Sale> queryWrapper = new QueryWrapper<>();
            QueryWrapper<Outsale> queryWrapper2 = new QueryWrapper<>();
            queryWrapper
                    .select("sum( money ) AS moneys,DATE_FORMAT( buytime, '%Y-%m' ) AS counttime")
                    .between("buytime",DateUtils.stepMonth(i),DateUtils.getMonth(i))
                    .groupBy("counttime")
                    .orderByDesc("counttime");

            queryWrapper2
                    .select("sum( outprice ) AS moneys,DATE_FORMAT( outtime, '%Y-%m' ) AS counttime")
                    .between("outtime",DateUtils.stepMonth(i),DateUtils.getMonth(i))
                    .groupBy("counttime")
                    .orderByDesc("counttime");

            Sale sales = saleService.getOne(queryWrapper);

            Outsale outsale = outsaleService.getOne(queryWrapper2);

            if(null!= sales){
                list1.add(sales.getCounttime());
                list2.add(sales.getMoneys());
                if(outsale!=null){
                    list3.add(sales.getMoneys()-outsale.getMoneys());
                    list4.add(-outsale.getMoneys());
                }else{
                    list3.add(sales.getMoneys());
                    list4.add(null);
                }
            }else {
                list1.add(new SimpleDateFormat("yyyy-MM").format(DateUtils.getMonth(i)));
                list2.add(null);
                if(outsale!=null){
                    list3.add(-outsale.getMoneys());
                    list4.add(-outsale.getMoneys());
                }else{
                    list3.add(null);
                    list4.add(null);
                }
            }
        }

        map.put("data1",list1);
        map.put("data2",list2);
        map.put("data3",list3);
        map.put("data4",list4);
        return new DataGridViewResult(map);
    }
*/

}
