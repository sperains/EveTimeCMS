package com.evetime.cms.service;

import com.evetime.cms.entity.Company;
import com.evetime.cms.entity.ResultData;

import java.util.List;

/**
 * Created by Rains
 * on 2016-06-04.
 */
public interface CompanyService {

    ResultData loadAll();

    ResultData findById(String companyId);

    ResultData add(Company company);

    ResultData add(String companyStr);

    ResultData update(Company company);

    ResultData recycle(String companyId);

    ResultData updateRalations(String companyId , List<String> brandIdList);
}
