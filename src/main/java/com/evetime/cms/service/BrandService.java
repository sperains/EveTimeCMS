package com.evetime.cms.service;

import com.evetime.cms.entity.Brand;
import com.evetime.cms.entity.ResultData;

/**
 * Created by Rains
 * on 2016-06-02.
 */
public interface BrandService {

    ResultData add(String brandStr);

    ResultData delete(String brandId);

    ResultData recycle(String brandId);

    ResultData update(Brand brand);

    ResultData findAll();

    ResultData findBrands(String companyId);


}
