package com.evetime.app.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by Rains
 * on 2016-07-04.
 */
public interface BranchSaleDataDao {

    /**
     * 查询今天的订单数
     * @param cloudId
     * @return
     */
    int queryTodayOrderCount(String cloudId );

    /**
     * 查询昨天的订单数
     * @param cloudId
     * @return
     */
    int queryYesterdayOrderCount(String cloudId);

    /**
     * 查询当天的菜品销量
     * @param cloudId
     * @return
     */
    int queryTodayFoodSellCount(String cloudId);

    /**
     * 查询昨天的菜品销量
     * @param cloudId
     * @return
     */
    int queryYesterDayFoodSellCount(String cloudId);

    /**
     * 查询当天的平均消费
     * @param cloudId
     * @return
     */
    double queryTodayAverageCost(String cloudId);

    /**
     * 查询昨天的平均消费
     * @param cloudId
     * @return
     */
    double queryYesterDayAverageCost(String cloudId);

    /**
     * 查询当天的翻台率
     * @param cloudId
     * @return
     */
    double queryTodayTableTurnOverRate(String cloudId);

    /**
     * 查询昨天的翻台率
     * @param cloudId
     * @return
     */
    double queryYesterDayTableTurnOverRate(String cloudId);

    /**
     * 查询分店七日营收
     * @param cloudId
     * @return
     *  date    日期
     *  sale    营业额
     */
    List<Map<String , Object>> queryWeekSale(String cloudId);

    List<Map<String , Object>> queryFoodSellInfo(String cloudId , int  Range);


    /**
     * 查询分店本周营收
     * @param cloudId
     * @return
     */
    List<Map<String , Object>> queryCurWeekSale(String cloudId);

    /**
     * 查询分店本月营收
     * @param cloudId
     * @return
     */
    List<Map<String , Object>> queryCurMonthSale(String cloudId);

    /**
     * 查询分店本年度营收
     * @param cloudId
     * @return
     */
    List<Map<String , Object>> queryCurYearSale(String cloudId);

    List<Map<String , Object>> queryMonthSale(String cloudid , int month);
}
