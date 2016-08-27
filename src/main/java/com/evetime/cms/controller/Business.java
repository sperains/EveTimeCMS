package com.evetime.cms.controller;

import com.evetime.cms.entity.ResultData;
import com.evetime.cms.service.BusinessService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Rains
 * on 2016-04-28.
 */
@Controller
@RequestMapping("/business")
public class Business {

    @Resource
    BusinessService businessService;

    public BusinessService getBusinessService() {
        return businessService;
    }

    public void setBusinessService(BusinessService businessService) {
        this.businessService = businessService;
    }


    @RequestMapping("/loadCompanys")
    @ResponseBody
    public ResultData loadCompanys(){

        ResultData result = businessService.loadAllCompanys();
        return result;
    }

    @RequestMapping("/loadBrands")
    @ResponseBody
    public ResultData loadBrands(){

        ResultData result = businessService.loadAllBrands();
        return result;
    }

    @RequestMapping(value = "/loadBrandsByCompanyId" , method = RequestMethod.POST)
    @ResponseBody
    public ResultData loadBrandsByCompanyId(String companyId){
        System.out.println(companyId);
        ResultData result = businessService.loadBrandsByCompanyId(companyId);
        return result;
    }

    @RequestMapping(value = "/loadBranchsByBrandId" , method = RequestMethod.POST)
    @ResponseBody
    public ResultData loadBranchsByBrandId(String brandId){
        System.out.println("brandId : " + brandId);
        ResultData result = businessService.loadBranchsByBrandId(brandId);
        return  result;
    }

}
