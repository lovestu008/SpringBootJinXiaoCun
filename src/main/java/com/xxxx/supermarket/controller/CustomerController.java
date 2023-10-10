package com.xxxx.supermarket.controller;
import com.xxxx.supermarket.annotation.RequiredPermission;
import com.xxxx.supermarket.annotation.SupLog;
import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.base.ResultInfo;
import com.xxxx.supermarket.entity.Customer;
import com.xxxx.supermarket.query.QueryCustomer;
import com.xxxx.supermarket.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/customer")
@Slf4j
@SupLog(type = "客户管理")
public class CustomerController extends BaseController {
    @Resource
    private CustomerService customerService;

    /**
     * 多条件查询
     * @param queryCustomer
     * @return
     */
    @RequiredPermission(code = "101010")
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryByParams(QueryCustomer queryCustomer){

        return customerService.queryByParams(queryCustomer);
    }

    /**
     *
     * @return
     */
    @RequiredPermission(code = "101010")
    @RequestMapping("index")
    public String index(){
        return "customer/customer";
    }


    @RequiredPermission(code = "101010")
    @RequestMapping("addCustomers")
    @ResponseBody
    @SupLog(content = "添加客户记录")
    public ResultInfo addCustomer(Customer customer){

        // 调用service层的方法
        customerService.addCustomer(customer);
        return success("添加超市客户成功");
    }


    /**
     *  更新超市客户
     * @param customer
     * @return com.xxxx.crm.base.ResultInfo
     */
    @RequiredPermission(code = "101010")
    @PostMapping("/update")
    @ResponseBody
    @SupLog(content = "更新客户记录")
    public ResultInfo updateCustomer(Customer customer) {
        // 调用Service层的添加方法
        customerService.updateCustomer(customer);
        return success("超市客户数据更新成功！");
    }

    /**
     *进入添加/修改营销机会数据页面
     * @return
     */
    @RequiredPermission(code = "101010")
    @RequestMapping("toCustomerPage")
    public String toCustomerPage(Integer customerId, HttpServletRequest request){
        // 判断customerId是否为空
        if (customerId != null) {
            // 通过ID查询营销机会数据
            Customer customer = customerService.selectByPrimaryKey(customerId);
            // 将数据设置到请求域中
            request.setAttribute("customer",customer);
        }
        return "customer/add_customer";

    }
    /**
     * 返回所有客户
     * @return
     */
    @RequiredPermission(code = "101010")
    @RequestMapping("allCustomers")
    @ResponseBody
    public List<Customer> allCustomers(){
        return customerService.allCustomers();
    }


    /**
     * 删除超市客户
     * @param ids
     * @return
     */
    @RequiredPermission(code = "101010")
    @PostMapping("/delete")
    @ResponseBody
    @SupLog(content = "删除客户记录")
    public ResultInfo deleteBeach(Integer[] ids){
        customerService.deleteBeach(ids);
        return success("超市客户数据删除成功");//这里需要返回的是一个成功与否的结果，而不是删除了几行
    }

}
