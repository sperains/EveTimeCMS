package com.evetime.cms.dao;

import com.evetime.cms.entity.CompanyUserRole;

import java.util.List;

/**
 * Created by Rains
 * on 2016-07-18.
 */
public interface CompanyUserRoleDao {

    int insert(List<CompanyUserRole> companyUserRoleList);

    int delete(List<CompanyUserRole> companyUserRoleList);

    int delete(String userId);

    List<CompanyUserRole> findAll(String userId);

    int update(String brandId , String cloudId);

}
