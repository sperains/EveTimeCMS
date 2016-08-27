package com.evetime.cms.dao;

import com.evetime.cms.entity.PayOrder;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-04-21.
 */
public interface OrderDao {

    //根据时间段查找支付订单信息.
    List<PayOrder> findPayInfoByTime(String startTime, String endTime, String start, String limit, String cloudId);

    //查询某一时间段内支付订单信息的总数和总金额
    Map<String , Object> findCountOfPayInfo(String startTime, String endTime, String cloudId);

    //根据支付订单号查找订单信息
    PayOrder findPayOrderByOrderNo(String orderNo);


    /**
     * 查询时间段内每天的总流水
     * @param startTime
     * @param endTime
     * @param cloudId
     * @return
     *      key
     *      sum           销售额
     *      dayTime    日期
     */
    List<Map<String,String>> findDayAmount(String startTime , String endTime , String cloudId);

    //查询每月的总流水
    List<Map<String,String>> findMonthAmountAmount(String year , String cloudId);

    /**
     *      查询某一时间段内每日的订单量
     * @param startTime
     * @param endTime
     * @param cloudId
     * @return
     *      key
     *      all                 所有订单
     *      finished        已完成
     *      dayTime        日期
     *
     */
    List<Map<String,Object>> findOrderCountOfDay(String startTime , String endTime , String cloudId);

    //查询月销售额
    List<Map<String,Object>> findAccountInfo(String month);


    /**
     *
     * @param month
     * @param companyId
     * @return  List<Map>
     *     key
     *     cloudId                   云端Id
     *     restaurantName      餐厅名称
     *     branchName           分店名称
     *     aliPay                      支付宝
     *     wechatPay              微信支付
     *
     */
    List<Map<String ,Object>> findAccountInfo(String month , String companyId);

    /**
     *
     * @param cloudId
     * @return List<Map>
     *     key
     *     sum                    销售额
     *     foodTypeName   菜品类别名称
     */
    List<Map<String , Object>> findAmountByFoodType(String cloudId);


    /**
     *  查询门店菜品销量
     * @param cloudId
     * @param limit
     * @return
     *      key
     *      foodName        菜品名称
     *      count                份数
     */
    List<Map<String , Object>> findFoodSellInfo(String cloudId , String limit);

    /**
     *  查询某个门店在某一时间段的客流量
     *
     *   @return
     *      key
     *      count       总人数
     *       dayTime  日期
     */
    List<Map<String , Object>> findFootFall(String cloudId , String startTime , String endTime);

    /**
     *  查询平均消费
     * @param cloudId
     * @return
     *  key
     *    avg       平均消费
     *    sum     总销售额
     *    count   总人数
     */
    List<Map<String , Object>> findAvgCost(String cloudId);

    /**
     *  查询消费次数
     * @param cloudId
     * @param month
     * @return
     *      key
     *      costTimes 消费次数
     *      count       人数
     */
    List<Map<String , Object>> findCostCount(String cloudId , String month);

    List<Map<String , Object>> findTableTurnoverRate(String cloudId ,String startTime , String endTime);

}
