package com.evetime.cms.service;

import com.evetime.cms.entity.CompanyUserRole;
import com.evetime.cms.entity.Permission;
import com.evetime.cms.entity.ResultData;
import com.evetime.cms.entity.User;

import java.util.List;

/**
 * Created by Rains
 * on 2016-04-20.
 */
public interface UserService {

    ResultData loadUsers();

    ResultData login(String userName , String password);

    ResultData addUser(User user);

    ResultData loadGroups();

    ResultData recycle(String userId);

    ResultData update(User user);

    ResultData insertPermission(List<Permission> permissionList);

    ResultData insertCompanyUserRole(String userId , List<CompanyUserRole> companyUserRoleList);

    ResultData loadChoosedShops(String userId);

    ResultData loadSettingShops(String userId);
}
