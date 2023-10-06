package com.xxxx.supermarket.controller;

import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.base.ResultInfo;
import com.xxxx.supermarket.model.UserModel;
import com.xxxx.supermarket.query.UserQuery;
import com.xxxx.supermarket.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    /**
     * 登录模块
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public ResultInfo userLogin(String userName,String userPwd){
        ResultInfo resultInfo = new ResultInfo();
        UserModel userModel = userService.userLogin(userName,userPwd);
        resultInfo.setResult(userModel);
        resultInfo.setCode(200);
        return resultInfo;
    }

    @RequestMapping("index")
    public String index(){
        return "user/user";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> userList(UserQuery userQuery){
        return userService.queryByParamsForTable(userQuery);
    }



}
