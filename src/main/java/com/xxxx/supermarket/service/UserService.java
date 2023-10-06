package com.xxxx.supermarket.service;

import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.base.ResultInfo;
import com.xxxx.supermarket.dao.UserMapper;
import com.xxxx.supermarket.entity.User;
import com.xxxx.supermarket.model.UserModel;
import com.xxxx.supermarket.utils.AssertUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService extends BaseService<User,Integer> {

    @Resource
    private UserMapper userMapper;

    /**
     * 用户登录模块service层
     * @param userName
     * @param userPwd
     * @return
     */
    public UserModel userLogin(String userName, String userPwd) {
        //参数验证
        AssertUtil.isTrue(null == userName,"用户名不能为空");
        AssertUtil.isTrue(null == userPwd,"密码不能为空");
        User user = userMapper.selectByUserByName(userName);
        //判断用户对象是否为空
        AssertUtil.isTrue(null == user,"用户不存在");

        UserModel userModel = new UserModel();

        return userModel;
    }





}
