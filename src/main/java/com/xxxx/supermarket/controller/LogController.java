package com.xxxx.supermarket.controller;

import com.xxxx.supermarket.aspect.SupLog;
import com.xxxx.supermarket.base.BaseController;
import com.xxxx.supermarket.base.ResultInfo;
import com.xxxx.supermarket.entity.Log;
import com.xxxx.supermarket.enums.StateStatus;
import com.xxxx.supermarket.query.LogQuery;
import com.xxxx.supermarket.service.LogService;
import com.xxxx.supermarket.utils.LoginUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping("log")
@Slf4j
@SupLog(type = "日志管理")
public class LogController extends BaseController {
    @Resource
    private LogService logService;
    //进入日志管理界面
    @RequestMapping("index")
    public String index(){
        return "log/log";
    }
    //日志数据查询（多条件分页查询）
    @RequestMapping("list")
    @ResponseBody //不加则认为返回视图
    public Map<String,Object> querySaleChanceByParams(LogQuery logQuery){
        System.out.println(logQuery);
        return logService.querylogByParams(logQuery);
    }
    @PostMapping("delete")
    @ResponseBody
    @SupLog(content = "批量删除日志记录")
    public ResultInfo deleteLog(Integer[] ids){
        logService.deleteLog(ids);
        return success("日志记录删除成功！");
    }
}
