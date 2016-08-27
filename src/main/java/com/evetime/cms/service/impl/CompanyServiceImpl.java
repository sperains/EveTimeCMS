package com.evetime.cms.service.impl;

import com.evetime.cms.dao.CompanyDao;
import com.evetime.cms.entity.Company;
import com.evetime.cms.entity.ResultData;
import com.evetime.cms.service.CompanyService;
import com.evetime.cms.util.JsonUtil;
import com.evetime.cms.util.UUIDGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rains
 *
 * on 2016-06-04.
 */
@Service("CompanyService")
public class CompanyServiceImpl implements CompanyService {

    @Resource(name = "CompanyDao")
    private CompanyDao companyDao;

    public CompanyDao getCompanyDao() {
        return companyDao;
    }

    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Override
    public ResultData loadAll() {

        ResultData result = new ResultData();

        List<Company> companyList = companyDao.findAll();

        result.setSuccess(true);
        result.setMessage("加载成功");
        result.setData(companyList);

        return result;
    }

    @Override
    public ResultData findById(String companyId) {
        return null;
    }

    @Override
    public ResultData add(Company company) {
        return null;
    }

    @Override
    public ResultData add(String companyStr) {

        ResultData result = new ResultData();
        Map<String,Object> map  = JsonUtil.JsonToMap(companyStr);

        List<Company> companyList = new ArrayList<Company>();
        String companyName = map.get("companyName").toString();
        String provinceId = map.get("provinceId").toString();
        String cityId = map.get("cityId").toString();
        String districtId = map.get("districtId").toString();
        String address = map.get("address").toString();

        List<String> brandIds = (List<String>) map.get("brandId");

        if(brandIds == null){
            result.setSuccess(false);
            result.setMessage("未选择下属品牌");
            return result;
        }

        Company company = new Company();
        company.setCompanyId(UUIDGenerator.getUUID());
        company.setCompanyName(companyName);
        company.setProvinceId(provinceId);
        company.setCityId(cityId);
        company.setDistrictId(districtId);
        company.setAddress(address);

        int n = 0;
        try {
            n = companyDao.insert(company);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(n == 0 ){
            result.setSuccess(false);
            result.setMessage("添加失败");
            return result;
        }

        List<Map<String,String>> ralations  = new ArrayList<Map<String, String>>();
        for(int i = 0 ; i < brandIds.size() ; i++){
            Map<String,String> ralation = new HashMap<String, String>();
            ralation.put("companyId" , company.getCompanyId());
            ralation.put("brandId" , brandIds.get(i));
            ralations.add(ralation);
        }

        companyDao.insert(ralations);

        result.setSuccess(true);
        result.setMessage("添加成功");
        return result;
    }

    @Override
    public ResultData update(Company company) {

        ResultData result = new ResultData();
        int n = companyDao.update(company);
        if(n == 0){
            result.setSuccess(false);
            result.setMessage("修改失败");
            return result;
        }

        result.setSuccess(true);
        result.setMessage("修改成功");

        return result;
    }

    @Override
    public ResultData recycle(String companyId) {

        ResultData result = new ResultData();

        if(companyId == null || "".equals(companyId)){
            result.setSuccess(false);
            result.setMessage("参数有误");
            return result;
        }

        int i = companyDao.recycle(companyId);

        if(i == 0){
            result.setSuccess(false);
            result.setMessage("删除失败,请稍后再试");
            return result;
        }

        companyDao.removeRalations(companyId);

        result.setSuccess(true);
        result.setMessage("删除成功");
        result.setData(i);
        return result;
    }

    @Override
    public ResultData updateRalations(String companyId, List<String> brandIdList) {

        ResultData result = new ResultData();

        int n = companyDao.removeRalations(companyId);

        List<Map<String, String>> ralations = new ArrayList<Map<String, String>>();



        for(int i = 0 ; i < brandIdList.size() ; i++){
            Map<String,String> map = new HashMap<String, String>();
            String brandId = brandIdList.get(i);
            map.put("companyId" , companyId);
            map.put("brandId",brandId);
            ralations.add(map);
        }

        companyDao.insert(ralations);



        result.setSuccess(true);
        result.setMessage("修改成功");
        return result;
    }
}
