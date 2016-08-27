package com.evetime.cms.service;

import com.evetime.cms.entity.ResultData;

/**
 * Created by Rains
 * on 2016-04-21.
 */
public interface OrderService {

    //根据选择的时间段加载订单信息.
    ResultData loadOrderPayInfoByTime(String startTime, String endTime, String start, String limit, String cloudId);

    //根据订单号加载订单信息
    ResultData loadOrderPayInfoByOrderNo(String orderNo);

    ResultData loadDayAmount(String startTime , String endTime , String cloudId);

    ResultData loadMonthAmount(String year , String cloudId);

    ResultData loadAccountInfo(String month);

    ResultData loadAccountInfo(String month , String companyId);

    ResultData loadAmountByFoodType(String cloudId);

    ResultData loadOrderCountOfDay(String startTime , String endTime , String cloudId);

    ResultData loadMonthAmountAmount(String year , String cloudId);

    ResultData loadFoodSellInfo(String cloudId , String limit);

    ResultData loadFootFall(String cloudId , String startTime , String endTime);

    ResultData loadAvgCost(String cloudId);

    ResultData loadSecondCosts(String cloudId , String month);

    ResultData loadTableTurnoverRate(String cloudId , String startTime , String endTime);

}
