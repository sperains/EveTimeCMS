package com.evetime.app.controller;

import com.evetime.app.dto.ResultData;
import com.evetime.app.service.ChartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Rains
 * on 2016-07-05.
 */
@Controller("AppChartController")
@RequestMapping("/chart")
public class ChartController {

    @Resource
    private ChartService chartService;

    @RequestMapping("/loadBrandData")
    @ResponseBody
    public ResultData loadBrandData(String userName , String password){

        ResultData result = chartService.loadBrandInfo(userName , password);
        return result ;
    }

    @RequestMapping("/loadBranchData")
    @ResponseBody
    public ResultData loadBranchData(String cloudId){

        ResultData result = chartService.loadBranchInfo(cloudId);
        return result ;
    }

    @RequestMapping("/loadFoodSellInfo")
    @ResponseBody
    public ResultData loadFoodSellInfo(String cloudId , int range){

        ResultData result = chartService.loadFoodSellInfo(cloudId , range);
        return result ;
    }

    @RequestMapping("/loadBrandSaleDetail")
    @ResponseBody
    public ResultData loadBrandSaleDetail(String id , int index){

        ResultData result = chartService.loadBrandSaleDetail(id , index);
        return result ;
    }

    @RequestMapping("loadBranchSaleDetail")
    @ResponseBody
    public ResultData loadBranchSaleDetail(String cloudId , int index){

        ResultData result = chartService.loadBranchSaleDetail(cloudId , index);
        return result ;
    }

    @RequestMapping("loadBranchList")
    @ResponseBody
    public ResultData loadBranchList(String id , int index ){

        ResultData result = chartService.loadBranchList(id , index);
        return result ;
    }

}
