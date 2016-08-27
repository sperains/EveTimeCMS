package com.evetime.cms.controller;

import com.evetime.cms.entity.Brand;
import com.evetime.cms.entity.ResultData;
import com.evetime.cms.service.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Rains
 * on 2016-06-02.
 */

@Controller("BrandController")
@RequestMapping("/brand")
public class BrandController {

    @Resource(name = "BrandService")
    private BrandService brandService;

    public BrandService getBrandService() {
        return brandService;
    }

    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public ResultData add(String key){

        ResultData result = brandService.add(key);
        return result;
    }

    @RequestMapping(value = "recycle")
    @ResponseBody
    public ResultData recycle(String brandId){

        ResultData result = brandService.recycle(brandId);

        return result;
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public ResultData update(Brand brand){
        if(brand == null){
            return new ResultData(false,"参数错误");
        }
        ResultData result = brandService.update(brand);
        return result;
    }

    @RequestMapping(value = "loadAll" ,method = RequestMethod.GET)
    @ResponseBody
    public ResultData loadAll(){

        ResultData result = brandService.findAll();
        return result;
    }

    @RequestMapping(value = "loadBrands" , method = RequestMethod.POST)
    @ResponseBody
    public ResultData loadBrands(String companyId){

        ResultData result = brandService.findBrands(companyId);
        return result;
    }


}
