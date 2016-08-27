package com.evetime.cms.controller;

import com.evetime.cms.entity.Company;
import com.evetime.cms.entity.ResultData;
import com.evetime.cms.service.CompanyService;
import com.evetime.cms.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Rains
 * on 2016-06-04.
 */
@Controller("CompanyController")
@RequestMapping("/company")
public class CompanyController {

    @Resource
    private CompanyService companyService;

    public CompanyService getCompanyService() {
        return companyService;
    }

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping("/loadAll")
    @ResponseBody
    public ResultData loadAll(){

        ResultData result = companyService.loadAll();
        return result;
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResultData add(String key){

        ResultData result = companyService.add(key);
        return result;
    }

    @RequestMapping("recycle")
    @ResponseBody
    public ResultData recycle(String companyId){

        ResultData result = companyService.recycle(companyId);
        return result;
    }

    @RequestMapping("updateRalations")
    @ResponseBody
    public ResultData updateRalations(String companyId , @RequestParam(value = "brandIdList")List<String> brandIdList){

        ResultData result = companyService.updateRalations(companyId , brandIdList);

        return result;
    }

    @RequestMapping(value = "update" ,method = RequestMethod.POST)
    @ResponseBody
    public  ResultData update(String key){
        ResultData result = new ResultData();
        Company company = JsonUtil.JsonToObject(key, Company.class);
        if(company == null){
            result.setSuccess(false);
            result.setMessage("参数错误");
            return result;
        }

        return companyService.update(company);
    }

}
