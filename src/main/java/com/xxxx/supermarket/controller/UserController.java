package com.xxxx.supermarket.controller;

import com.xxxx.supermarket.annotation.RequiredPermission;
import com.xxxx.supermarket.aspect.SupLog;
import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.base.ResultInfo;
import com.xxxx.supermarket.entity.User;
import com.xxxx.supermarket.model.UserModel;
import com.xxxx.supermarket.query.UserQuery;
import com.xxxx.supermarket.service.UserService;
import com.xxxx.supermarket.utils.LoginUserUtil;
import com.xxxx.supermarket.utils.PhoneUtil;
import com.xxxx.supermarket.utils.UserIDBase64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("user")
@Slf4j
@SupLog(type = "用户管理")
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
    @RequiredPermission(code = "5020")
    @RequestMapping("index")
    public String index(){
        return "user/user";
    }
    @RequiredPermission(code = "5020")
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> userList(UserQuery userQuery){
        return userService.queryByParamsForTable(userQuery);
    }
    @RequiredPermission(code = "5020")
    @RequestMapping("addOrUpdateUserPage")
    public String addOrUpdateUserPage(Integer id, HttpServletRequest request){
        if (id != null){
            request.setAttribute("userById",userService.selectByPrimaryKey(id));
        }
        return "user/add_update";
    }
    @RequiredPermission(code = "5020")
    @RequestMapping("add")
    @ResponseBody
    @SupLog(content = "添加用户记录操作")
    public ResultInfo addUser(User user){
        userService.addUser(user);
        return success("用户添加成功");
    }
    @RequiredPermission(code = "5020")
    @RequestMapping("update")
    @ResponseBody
    @SupLog(content = "修改用户记录操作")
    public ResultInfo updateUser(User user){
        userService.updateUser(user);
        return success("用户修改成功");
    }
    @RequiredPermission(code = "5020")
    @RequestMapping("delete")
    @ResponseBody
    @SupLog(content = "删除用户记录操作")
    public ResultInfo deleteUser(Integer[] ids){
        userService.deleteUser(ids);
        return success("用户删除成功");
    }
    @RequiredPermission(code = "5020")
    @RequestMapping("toPasswordPage")
    public String toPasswordPage(HttpServletRequest request,Integer userId){
        if (null!=userId){
            request.setAttribute("userId",userId);
        }
        return "user/password";
    }
    @RequiredPermission(code = "5020")
    @RequestMapping("updatePwd")
    @ResponseBody
    @SupLog(content = "修改用户密码")
    public ResultInfo updatePwd(HttpServletRequest request,String oldPassword,String newPassword,String repeatPassword,Integer userId){
        Integer id = LoginUserUtil.releaseUserIdFromCookie(request);
        userService.updatePwd(id,userId,oldPassword,newPassword,repeatPassword);
        if (userId != null && id != userId){
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setMsg("修改密码成功");
            resultInfo.setCode(201);
            return resultInfo;
        }
        return success("修改密码成功");
    }
}
