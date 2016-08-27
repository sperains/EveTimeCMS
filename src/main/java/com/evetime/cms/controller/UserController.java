package com.evetime.cms.controller;

import com.evetime.cms.entity.CompanyUserRole;
import com.evetime.cms.entity.Permission;
import com.evetime.cms.entity.ResultData;

import com.evetime.cms.entity.User;
import com.evetime.cms.service.UserService;
import com.evetime.cms.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rains
 * on 2016-05-13.
 */
@Controller("UserController")
@RequestMapping("/user")
public class UserController {

    @Resource(name = "UserService")
    private UserService userService ;

    @RequestMapping(value = "/loadUsers" ,method = RequestMethod.GET)
    @ResponseBody
    public ResultData loadAll(){

        ResultData result = userService.loadUsers();

        return result;
    }

    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    @ResponseBody
    public ResultData login(String userName ,String password){
        //System.out.print("login controller");
        ResultData result = userService.login(userName,password);
        return result;
    }

     @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    @ResponseBody
    public ResultData add(String key){
        ResultData result = new ResultData();

        User user = JsonUtil.JsonToObject(key, User.class);

        if(user == null){
            result.setSuccess(false);
            result.setMessage("参数传递有误");
            return result;
        }

        result = userService.addUser(user);

        return result;
    }


    @RequestMapping(value = "/loadGroups" ,method = RequestMethod.GET)
    @ResponseBody
    public ResultData loadGroups(){

        ResultData result = userService.loadGroups();
        return result;
    }

    @RequestMapping(value = "/recycle" , method = RequestMethod.POST)
    @ResponseBody
    public ResultData recycle(String userId){

        ResultData result = userService.recycle(userId);
        return result;
    }

    @RequestMapping(value = "update" ,method = RequestMethod.POST)
    @ResponseBody
    public ResultData update(String key){

        ResultData result = new ResultData();
        User user = JsonUtil.JsonToObject(key ,User.class);

        if(user == null){
            result.setSuccess(false);
            result.setMessage("参数错误");
            return result;
        }

        return userService.update(user);
    }

    @RequestMapping(value = "updatePermission" , method = RequestMethod.POST)
    @ResponseBody
    public ResultData updatePermission(String  key){

        ResultData result = new ResultData();

        List<String> permissionList = JsonUtil.JsonToList(key ,String[].class);

        List<Permission> list = new ArrayList<Permission>();
        for(int i = 0 ; i < permissionList.size() ; i++){
            Permission permission = JsonUtil.JsonToObject(permissionList.get(i) , Permission.class);
            if(permission == null){
                result.setSuccess(false);
                result.setMessage("参数错误");
                return result;
            }
            list.add(permission);
        }

        return userService.insertPermission(list);
    }

    @RequestMapping(value = "shopSetting")
    @ResponseBody
    public ResultData shopSetting(/*@RequestParam(value = "userRoleList" )*/String userRoleList , String userId){

        List<CompanyUserRole> companyUserRoleList =  JsonUtil.JsonToList(userRoleList , CompanyUserRole[].class);

        ResultData result = userService.insertCompanyUserRole(userId , companyUserRoleList);
        return result ;
    }

    @RequestMapping(value = "loadChoosedShops")
    @ResponseBody
    public ResultData loadChoosedShops(String userId){

        ResultData result = userService.loadChoosedShops(userId);
        return result ;
    }

    @RequestMapping(value = "loadSettingShops")
    @ResponseBody
    public ResultData loadSettingShops(String companyId){

        ResultData result = userService.loadSettingShops(companyId);
        return result ;
    }


}
