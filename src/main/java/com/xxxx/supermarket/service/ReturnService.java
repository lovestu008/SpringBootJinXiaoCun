package com.xxxx.supermarket.service;

import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.dao.ReturnMapper;
import com.xxxx.supermarket.entity.Return;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ReturnService extends BaseService<Return,Integer> {
    @Resource
    private ReturnMapper returnMapper;
    public List<Map<String,Object>> selectAllProvider(){
        List<Map<String, Object>> maps = returnMapper.selectAllProvider();
        System.out.println(maps);
        return returnMapper.selectAllProvider();
    }
    public List<Map<String, Object>> selectAllGoodsName() {

        return returnMapper.selectAllGoodsName();
    }

}
