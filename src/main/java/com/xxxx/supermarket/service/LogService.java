package com.xxxx.supermarket.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.dao.LogMapper;
import com.xxxx.supermarket.entity.Log;
import com.xxxx.supermarket.query.LogQuery;
import com.xxxx.supermarket.utils.AssertUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
@Service
public class LogService extends BaseService<Log,Integer> {
    @Resource
    private LogMapper logMapper;
    //批量删除日志
@Transactional(propagation = Propagation.REQUIRED)
    public void deleteLog(Integer[] ids) {
    //判断id是否为空
    AssertUtil.isTrue(ids.length<1 || ids==null,"待删除记录不存在！");
    //执行删除操作，判断受影响的行数
    AssertUtil.isTrue(logMapper.deleteBatch(ids)!=ids.length,"日志记录删除失败");
    }
    //条件查询日志列表
    public Map<String, Object> querylogByParams(LogQuery logQuery) {
        Map<String,Object> map = new HashMap<>();
        //开启分页
        PageHelper.startPage(logQuery.getPage(),logQuery.getLimit());
        //得到对应分页对象
        PageInfo<Log> pageInfo = new PageInfo<>(logMapper.queryByParams(logQuery));
        //设置map对象
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        //设置分页好的列表
        map.put("data",pageInfo.getList());
        return map;
    }
}
