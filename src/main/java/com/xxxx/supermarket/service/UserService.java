package com.xxxx.supermarket.service;

import com.xxxx.supermarket.base.BaseService;
import com.xxxx.supermarket.base.ResultInfo;
import com.xxxx.supermarket.dao.UserMapper;
import com.xxxx.supermarket.dao.UserRoleMapper;
import com.xxxx.supermarket.entity.User;
import com.xxxx.supermarket.entity.UserRole;
import com.xxxx.supermarket.model.UserModel;
import com.xxxx.supermarket.utils.AssertUtil;
import com.xxxx.supermarket.utils.Md5Util;
import com.xxxx.supermarket.utils.PhoneUtil;
import com.xxxx.supermarket.utils.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserService extends BaseService<User,Integer> {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    /**
     * 用户登录模块service层
     * @param userName
     * @param userPwd
     * @return
     */
    public UserModel userLogin(String userName, String userPwd) {
        //参数验证
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"密码不能为空");
        User user = userMapper.selectUserByName(userName);
        User user1 = userMapper.selectUserByEmail(userName);
        User temp = null;
        //判断用户对象是否为空
        AssertUtil.isTrue(null == user && null == user1,"用户不存在");
        //用户对象不为空
        UserModel userModel = new UserModel();
        userPwd = Md5Util.encode(userPwd);
        if (null == user){
            temp = user1;
        }else if (null == user1){
            temp = user;
        }
        AssertUtil.isTrue(!temp.getPassword().equals(userPwd) ,"密码错误" );
        //如果是用户名登录正确时
        return buildModel(temp);
    }

    /**
     * 构建返回给用户的信息
     * @param user
     * @return
     */
    private UserModel buildModel(User user) {
        UserModel userModel = new UserModel();
        //设置用户信息
        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        userModel.setUserName(user.getUserName());
        userModel.setTrueName(user.getTrueName());
        return userModel;
    }

    /**
     * service 用户添加操作
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(User user) {
        //1. 参数校验
        checkAddOrUpdateParams(user.getUserName(),user.getEmail(),user.getTrueName(),user.getPhone(),null);
        user.setCreatetime(new Date());
        user.setUpdatetime(new Date());
        user.setPassword(Md5Util.encode("123456"));
        user.setIsDel(0);
        Integer key = userMapper.insertSelective(user);
        //执行添加 判断结果
        AssertUtil.isTrue( key < 1 || key == null ,"添加用户操作失败");
        //用户角色分配
        relationUserRole(user.getId(),user.getRoleIds());
    }

    /**
     * 用户角色分配
     * @param id
     * @param roleIds
     */
    private void relationUserRole(Integer id, String roleIds) {
        Integer count = userRoleMapper.countUserRoleByUserId(id);
        if (count>0){
            AssertUtil.isTrue(userRoleMapper.deleteByUserId(id)<count,"用户角色分配失败");
        }
        if(!StringUtils.isBlank(roleIds)){
            List<UserRole> userRoles = new ArrayList<>();
            for (String s:roleIds.split(",")){
                UserRole userRole = new UserRole();
                userRole.setUserId(id);
                userRole.setRoleId(Integer.parseInt(s));
                userRoles.add(userRole);
            }
            AssertUtil.isTrue(userRoleMapper.insertBatch(userRoles)<userRoles.size(),"用户角色分配失败");
        }
    }

    /**
     * service用户修改操作
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(User user) {
        AssertUtil.isTrue(null == user.getId(),"待更新用户不存在");
        User temp = userMapper.selectByPrimaryKey(user.getId());
        //参数验证
        AssertUtil.isTrue(null == temp,"待更新记录不存在");
        checkAddOrUpdateParams(user.getUserName(), user.getEmail(),user.getTrueName(), user.getPhone(), user.getId());
        user.setUpdatetime(new Date());
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user)<1,"用户更新失败");
        relationUserRole(user.getId(),user.getRoleIds());
    }

    /**
     * service用户删除操作
     * @param ids
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(Integer[] ids) {
        AssertUtil.isTrue(null == ids || ids.length < 1, "待删除用户不存在");

        AssertUtil.isTrue(deleteBatch(ids) != ids.length,"用户记录删除失败");
        for (Integer userId:ids){
            Integer count = userRoleMapper.countUserRoleByUserId(userId);
            if (count>0){
                AssertUtil.isTrue(userRoleMapper.deleteByUserId(userId) < count,"删除用户失败");
            }
        }
    }


    /**
     * 添加或修改用户的参数验证
     * @param userName
     * @param email
     * @param phone
     * @param id
     */
    private void checkAddOrUpdateParams(String userName, String email,String trueName, String phone, Integer id) {
        //⽤户名 ⾮空 唯⼀性
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名不能为空");
        User temp = userMapper.selectUserByName(userName);
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(id)) ,"用户名不唯一");
        //邮箱 ⾮空
        AssertUtil.isTrue(StringUtils.isBlank(email), "邮箱不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(trueName), "用户真实姓名不能为空");
        //⼿机号 ⾮空 格式合法
        AssertUtil.isTrue(StringUtils.isBlank(phone) , "手机号码不能为空");
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone),"手机号不正确");
    }


    /**
     * 修改密码的值校验
     * @param oldPassword
     * @param newPassword
     * @param repeatPassword
     */
    private void checkParams(String oldPassword, String newPassword, String repeatPassword){
        AssertUtil.isTrue(StringUtils.isBlank(oldPassword),"请输入旧密码");
        AssertUtil.isTrue(StringUtils.isBlank(newPassword),"请输入新密码");
        AssertUtil.isTrue(StringUtils.isBlank(repeatPassword),"请再次输入新密码");
        AssertUtil.isTrue(!newPassword.equals(repeatPassword),"输入的新密码不一致");
    }

    /**
     * 修改密码模块Service层
     * @param oldPassword
     * @param newPassword
     * @param repeatPassword
     */
    public void updatePwd(Integer id,Integer userId,String oldPassword, String newPassword, String repeatPassword) {
        checkParams(oldPassword, newPassword, repeatPassword);
        User temp = null;
        if (null == userId) {
            temp = userMapper.selectByPrimaryKey(id);
        }else {
           temp = userMapper.selectByPrimaryKey(userId);
        }
        oldPassword = Md5Util.encode(oldPassword);
        AssertUtil.isTrue(!temp.getPassword().equals(oldPassword),"旧密码错误");
        //执行更新操作
        temp.setPassword(Md5Util.encode(newPassword));
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(temp) < 1,"密码修改失败");
    }
}
