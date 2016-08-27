package com.evetime.cms.service;

import com.evetime.cms.entity.ResultData;

/**
 * Created by Rains
 * on 2016-04-28.
 */
public interface BusinessService {

    ResultData loadAllCompanys();

    ResultData loadAllBrands();

    ResultData loadBrandsByCompanyId(String companyId);

    ResultData loadBranchsByBrandId(String brandId);

}
