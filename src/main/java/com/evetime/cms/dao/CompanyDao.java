package com.evetime.cms.dao;


import com.evetime.cms.entity.Company;

import java.util.List;
import java.util.Map;

/**
 * Created by Rains
 * on 2016-06-04.
 */
public interface CompanyDao {

     List<Company> findAll();

     List<Company> findByCompanyId(String companyId);

     int insert(Company company);

    int recycle(String companyId);

     int update(Company company);

    void insert(List<Map<String,String>> ralations);

    int removeRalations(String companyId);

    void delete(List<Map<String,String>> ralations);

    List<String> findBrandId(String companyId);

}
