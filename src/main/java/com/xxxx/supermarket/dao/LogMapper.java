package com.xxxx.supermarket.dao;

import com.xxxx.supermarket.base.BaseMapper;
import com.xxxx.supermarket.entity.Log;
import com.xxxx.supermarket.query.LogQuery;

import java.util.List;
import java.util.Map;

public interface LogMapper extends BaseMapper<Log,Integer> {
    List<Log> queryByParams(LogQuery logQuery);

}