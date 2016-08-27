package com.evetime.cms.service.impl;

import com.evetime.cms.dao.CompanyUserRoleDao;
import com.evetime.cms.dao.ShopDao;
import com.evetime.cms.dao.impl.UserDao;
import com.evetime.cms.entity.*;
import com.evetime.cms.service.UserService;
import com.evetime.cms.util.JsonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rains
 * on 2016-04-20.
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Resource(name = "UserDao")
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Resource(name = "CompanyUserRoleDao")
    private CompanyUserRoleDao companyUserRoleDao;

    public CompanyUserRoleDao getCompanyUserRoleDao() {
        return companyUserRoleDao;
    }

    public void setCompanyUserRoleDao(CompanyUserRoleDao companyUserRoleDao) {
        this.companyUserRoleDao = companyUserRoleDao;
    }

    @Resource(name = "ShopDao")
    private ShopDao shopdao;

    public ShopDao getShopdao() {
        return shopdao;
    }

    public void setShopdao(ShopDao shopdao) {
        this.shopdao = shopdao;
    }

    public ResultData loadUsers() {

        ResultData result = new ResultData();

        List<User> userList = userDao.findAllUsers();
        result.setSuccess(true);
        result.setData(userList);
        result.setMessage("加载成功");


        return result;
    }

    @Override
    public ResultData login(String userName , String password) {

        ResultData result = new ResultData();

        if(userName == null || "".equals(userName)){
            result.setSuccess(false);
            result.setMessage("参数传递错误");
            return result;
        }

        List<User> userList = userDao.findUser(userName);
        if(userList.size() == 0){
            result.setSuccess(false);
            result.setMessage("用户名不存在");
            return result;
        }

        User user = userList.get(0);

        if(!user.getPassword().equals(password)){
            result.setSuccess(false);
            result.setMessage("密码错误");
            return result;
        }

        result.setSuccess(true);
        result.setMessage("登陆成功");
        user.setPassword(null);
        result.setData(user);

        return result;
    }


    @Override
    public ResultData addUser(User user) {


        ResultData result = new ResultData();

        int n = userDao.insert(user);

        if(n == 0){
            result.setSuccess(false);
            result.setMessage("添加失败");
            return result;
        }

        result.setSuccess(true);
        result.setMessage("添加成功");
        return  result;

    }

    @Override
    public ResultData loadGroups() {

        ResultData result = new ResultData();

        List<Group> groups = userDao.loadGroups();

        result.setSuccess(true);
        result.setMessage("加载分组列表成功");
        result.setData(groups);

        return result;
    }

    @Override
    public ResultData recycle(String userId) {

        ResultData result = new ResultData();

        if(userId == null || "".equals(userId)){
            result.setSuccess(false);
            result.setMessage("参数有误");
            return result;
        }


        int i = userDao.recycle(userId);

        if(i == 0){
            result.setSuccess(false);
            result.setMessage("删除失败,请稍后再试");
            return result;
        }

        result.setSuccess(true);
        result.setMessage("删除成功");
        result.setData(i);
        return result;
    }

    @Override
    public ResultData update(User user) {

        ResultData result = new ResultData();

        int n = userDao.update(user);

        if( n== 0 ){
            result.setSuccess(false);
            result.setMessage("修改失败");
            return result;
        }

        result.setSuccess(true);
        result.setMessage("修改成功");
        return result;
    }

    @Override
    public ResultData insertPermission(List<Permission> permissionList) {

        ResultData result = new ResultData();

        Permission permission = permissionList.get(0);
        String userId = permission.getUserId();
        userDao.deletePermission(userId);

        userDao.insertPermission(permissionList);
        result.setSuccess(true);
        result.setMessage("设置成功");

        return result;
    }

    @Override
    public ResultData insertCompanyUserRole(String userId , List<CompanyUserRole> companyUserRoleList) {

        ResultData result = new ResultData();

        //获取该用户之前所管理的门店列表
        List<CompanyUserRole> oldCompanyUserRoleList = companyUserRoleDao.findAll(userId);


        List<CompanyUserRole> delShopList = getShopList(new ArrayList<CompanyUserRole>(oldCompanyUserRoleList) , new ArrayList<CompanyUserRole>(companyUserRoleList));
        List<CompanyUserRole> addShopList = getShopList(new ArrayList<CompanyUserRole>(companyUserRoleList) , new ArrayList<CompanyUserRole>(oldCompanyUserRoleList));

        int delCount = companyUserRoleDao.delete(delShopList);

        System.out.println(delCount + "条数据被删除");

        int addCount = companyUserRoleDao.insert(addShopList);

        System.out.println(addCount + "条数据被添加");

        result.setSuccess(true);
        result.setMessage("修改成功");
        return result;
    }

    @Override
    public ResultData loadChoosedShops(String userId) {

        ResultData result = new ResultData();

        List<CompanyUserRole> choosedShopList = companyUserRoleDao.findAll(userId);

        result.setSuccess(true);
        result.setMessage("加载成功");
        result.setData(choosedShopList);
        return result;
    }

    @Override
    public ResultData loadSettingShops(String companyId) {

        ResultData result = new ResultData();

        List<Shop> settingShopList = shopdao.findbyCompanyId(companyId);

        result.setSuccess(true);
        result.setMessage("加载成功");
        result.setData(settingShopList);
        return result;
    }

    public List<CompanyUserRole> getShopList(List<CompanyUserRole> oldList , List<CompanyUserRole> newList){

        oldList.removeAll(newList);
        return oldList;
    }

}
