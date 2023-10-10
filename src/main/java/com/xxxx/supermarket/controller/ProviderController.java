package com.xxxx.supermarket.controller;

import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.base.ResultInfo;
import com.xxxx.supermarket.entity.Provider;
import com.xxxx.supermarket.query.QueryProvider;
import com.xxxx.supermarket.service.ProviderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("provider")
public class ProviderController extends BaseController {

    @Resource
    private ProviderService providerService;

    /**
     * 多条件查询
     * @param queryProvider
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryProviderByParams(QueryProvider queryProvider){

        return providerService.queryByParams(queryProvider);
    }

    /**
     *返回供货商视图
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "provider/provider";
    }


    /**
     * 添加供应商
     * @param provider
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public ResultInfo addProvider(Provider provider){

        // 调用service层的方法
        providerService.addProvider(provider);
        return success("添加超市客户成功");
    }


    /**
     *  更新供应商数据
     * @param provider
     * @return com.xxxx.crm.base.ResultInfo
     */
    @PostMapping("/update")
    @ResponseBody
    public ResultInfo updateProvider(Provider provider) {
        // 调用Service层的添加方法
        providerService.updateProvider(provider);
        return success("超市客户数据更新成功！");
    }



    /**
     *进入添加/修改供应商数据页面
     * @return
     */
    @RequestMapping("toProviderPage")
    public String toProviderPage(Integer providerId, HttpServletRequest request){
        // 判断customerId是否为空
        if (providerId != null) {
            // 通过ID查询营销机会数据
            Provider provider = providerService.selectByPrimaryKey(providerId);
            // 将数据设置到请求域中
            request.setAttribute("provider",provider);
        }
        return "provider/add_provider";

    }

    /**
     * 删除供应商
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public ResultInfo deleteBeach(Integer[] ids){
        providerService.deleteBeach(ids);
        return success("供应商数据删除成功");//这里需要返回的是一个成功与否的结果，而不是删除了几行
    }
}
