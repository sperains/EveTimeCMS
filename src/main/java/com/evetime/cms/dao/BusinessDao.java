package com.evetime.cms.dao;


import com.evetime.cms.entity.Branch;
import com.evetime.cms.entity.Brand;
import com.evetime.cms.entity.Company;

import java.util.List;

/**
 * Created by Rains
 * on 2016-04-28.
 */
public interface BusinessDao {

    List<Company> findAllCompanys();

    List<Brand> findAllBrands();

    List<Brand> findBrandsByCompanyId(String companyId);

    List<Branch> findBranchsByBrandId(String brandId);

}
