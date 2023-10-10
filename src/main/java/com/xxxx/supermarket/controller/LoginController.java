package com.xxxx.supermarket.controller;

import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.dao.RoleMenuMapper;
import com.xxxx.supermarket.entity.User;
import com.xxxx.supermarket.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import com.xxxx.supermarket.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LoginController extends BaseController {

    @Resource
    private UserService userService;

    @Resource
    private RoleMenuMapper roleMenuMapper;


    /**
     * 系统登录⻚
     *
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "index";
    }

    // 系统界⾯欢迎⻚
    @RequestMapping("welcome")
    public String welcome() {
        return "welcome";
    }

    /**
     * 后端管理主⻚⾯
     *
     * @return
     */
    @RequestMapping("main")
    public String main(HttpServletRequest request) {
        //查询用户对象,设置到session作用域
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        User user = userService.selectByPrimaryKey(userId);
        request.getSession().setAttribute("user", user);

        List<String> permissions = roleMenuMapper.queryUserHasRoleHasPermissionByUserId(userId);
        request.getSession().setAttribute("permissions",permissions);
        return "main";
    }

}
