package com.evetime.cms.service.impl;

import com.evetime.cms.dao.BusinessDao;
import com.evetime.cms.entity.*;
import com.evetime.cms.service.BusinessService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Rains
 * on 2016-04-28.
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    @Resource
    private BusinessDao businessDao;

    public BusinessDao getBusinessDao() {
        return businessDao;
    }

    public void setBusinessDao(BusinessDao businessDao) {
        this.businessDao = businessDao;
    }

    public ResultData loadAllCompanys() {

        ResultData result = new ResultData();

        List<Company> companys = businessDao.findAllCompanys();
        result.setSuccess(true);
        result.setMessage("加载总公司列表成功");
        result.setData(companys);
        return result;
    }

    public ResultData loadAllBrands() {

        ResultData result = new ResultData();

        List<Brand> brands = businessDao.findAllBrands();

        result.setSuccess(true);
        result.setMessage("加载品牌列表成功");
        result.setData(brands);
        return result;
    }

    public ResultData loadBrandsByCompanyId(String companyId) {

        ResultData result = new ResultData();

        List<Brand> brands = businessDao.findBrandsByCompanyId(companyId);

        result.setSuccess(true);
        result.setMessage("加载品牌列表成功");
        result.setData(brands);
        return result;
    }

    public ResultData loadBranchsByBrandId(String brandId) {

        ResultData result = new ResultData();

        List<Branch> branchs = businessDao.findBranchsByBrandId(brandId);

        result.setSuccess(true);
        result.setMessage("加载分店成功");
        result.setData(branchs);

        return result;
    }

}
