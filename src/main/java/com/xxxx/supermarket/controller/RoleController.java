package com.xxxx.supermarket.controller;

import com.xxxx.supermarket.aspect.SupLog;
import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.base.ResultInfo;
import com.xxxx.supermarket.dao.RoleMapper;
import com.xxxx.supermarket.entity.Role;
import com.xxxx.supermarket.query.RoleQuery;
import com.xxxx.supermarket.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.util.Map;

@Controller
@RequestMapping("role")
@Slf4j
@SupLog(type = "角色管理")
public class RoleController extends BaseController{

    @Resource
    private RoleService roleService;
    @Resource
    private RoleMapper roleMapper;

    @RequestMapping("index")
    public String index(){
        return "role/role";
    }

    @ResponseBody
    @RequestMapping("list")
    public Map<String,Object> roleList(RoleQuery roleQuery){
        return roleService.queryByParamsForTable(roleQuery);
    }

    @RequestMapping("addOrUpdateRolePage")
    public String toAddOrUpdateRolePage(Integer id, HttpServletRequest request){
        if (null != id){
            Role role = roleMapper.selectByPrimaryKey(id);
            request.setAttribute("role",role);
        }
        return "role/add_update";
    }

    @RequestMapping("add")
    @ResponseBody
    @SupLog(content = "添加角色记录")
    public ResultInfo roleAdd(Role role){
        System.out.println(role);
        roleService.addRole(role);
        return success("角色添加成功");
    }

    @RequestMapping("update")
    @ResponseBody
    @SupLog(content = "修改角色记录")
    public ResultInfo roleUpdate(Role role){
        roleService.updateRole(role);
        return success("角色修改成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    @SupLog(content = "删除角色记录")
    public ResultInfo roleDelete(Integer id){
        roleService.deleteRole(id);
        return success("角色删除成功");
    }

}
