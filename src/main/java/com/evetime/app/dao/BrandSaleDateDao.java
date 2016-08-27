package com.evetime.app.dao;

import com.evetime.app.entity.Branch;

import java.util.List;
import java.util.Map;

/**
 * Created by Rains
 * on 2016-07-09.
 */
public interface BrandSaleDateDao {

    /**
     * 查询品牌一周内销售额
     * @param userId
     * @return
     */
    List<Map<String,  Object>> querySaleOfWeek(String userId);

    /**
     * 查询品牌本周销售额
     * @param userId
     * @return
     */
    List<Map<String , Object>> queryCurWeekSale(String userId);

    /**
     * 查询品牌本月销售额
     * @param userId
     * @return
     */
    List<Map<String , Object>> queryCurMonthSale(String userId);

    /**
     * 查询品牌本年度销售额
     * @param userId
     * @return
     */
    List<Map<String , Object>> queryCurYearSale(String userId);


    /**
     * 查询品牌指定月份的销售额
     * @param userId
     * @param month (1代表1月, 以此类推)
     * @return
     */
    List<Map<String , Object>> queryMonthSale(String userId , int month) ;

    List<Branch> queryCurWeekBranchList(String userId);

    List<Branch> queryCurMonthBranchList(String userId);

    List<Branch> queryCurYearBranchList(String userId);

    List<Branch> queryMonthBranchList(String userId , int month);

    double queryCurDaySale(String userId);

}
