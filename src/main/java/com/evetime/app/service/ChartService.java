package com.evetime.app.service;

import com.evetime.app.dto.ResultData;

/**
 * Created by Rains
 * on 2016-07-04.
 */
public interface ChartService {

    ResultData loadBrandInfo(String userName , String password);

    ResultData loadBranchInfo(String cloudId );

    ResultData loadFoodSellInfo(String cloudId , int range) ;

    ResultData loadBrandSaleDetail(String userId , int index);

    ResultData loadBranchSaleDetail(String cloudId , int index);

    ResultData loadBranchList(String id , int index);
}
