package com.evetime.cms.service.impl;

import com.evetime.cms.dao.impl.BrandDao;
import com.evetime.cms.entity.Brand;
import com.evetime.cms.entity.ResultData;
import com.evetime.cms.service.BrandService;
import com.evetime.cms.util.JsonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Rains
 * on 2016-06-02.
 */
@Service("BrandService")
public class BrandServiceImpl implements BrandService {

    @Resource(name = "BrandDao")
    private BrandDao brandDao;

    public BrandDao getBrandDao() {
        return brandDao;
    }

    public void setBrandDao(BrandDao brandDao) {
        this.brandDao = brandDao;
    }

    @Override
    public ResultData add(String brandStr) {
        ResultData result = new ResultData();

        Brand brand = JsonUtil.JsonToObject(brandStr , Brand.class);

        if(brand == null ){
            result.setSuccess(false);
            result.setMessage("参数错误");
            return result;
        }

        int n = brandDao.insert(brand);

        if(n == 0){
            result.setSuccess(false);
            result.setMessage("添加失败,请稍后再试");
            return result;
        }

        result.setSuccess(true);
        result.setMessage("添加成功");
        result.setData(n);

        return result;
    }

    @Override
    public ResultData delete(String brandId) {
        return null;
    }

    @Override
    public ResultData recycle(String brandId) {

        ResultData result = new ResultData();

        if(brandId == null || "".equals(brandId)){
            result.setSuccess(false);
            result.setMessage("参数不能为空");
            return result;
        }


        int n = brandDao.recycle(brandId);

        if(n == 0){
            result.setSuccess(false);
            result.setMessage("修改信息失败,请稍后再试");
            return result;
        }

        result.setSuccess(true);
        result.setMessage("修改成功");
        result.setData(n);
        return result;
    }

    @Override
    public ResultData update(Brand brand) {
        ResultData result = new ResultData();

        int  n = brandDao.update(brand);

        if(n == 0){
            result.setSuccess(false);
            result.setMessage("更新失败");
            return result;
        }
        result.setSuccess(true);
        result.setMessage("更新成功");
        return result;
    }

    @Override
    public ResultData findAll() {

        ResultData result = new ResultData();
        List<Brand> brandList = brandDao.findAll();

        result.setSuccess(true);
        result.setMessage("查找品牌列表成功");
        result.setData(brandList);
        return result;
    }

    @Override
    public ResultData findBrands(String companyId) {

        ResultData result = new ResultData();
        List<Brand> brandIdList = brandDao.findBrands(companyId);

        result.setSuccess(true);
        result.setMessage("加载品牌列表成功");
        result.setData(brandIdList);
        return result;
    }
}
