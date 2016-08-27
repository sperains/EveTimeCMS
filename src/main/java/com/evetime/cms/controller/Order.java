package com.evetime.cms.controller;

import com.evetime.cms.entity.ResultData;
import com.evetime.cms.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Rains
 * on 2016-04-21.
 */

@Controller
@RequestMapping("order")
public class Order {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "OrderService")
    private OrderService orderService;

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService OrderService) {
        this.orderService = OrderService;
    }


    /**
     * 根据时间段查找分店支付信息
     * @param startTime
     * @param endTime
     * @param start
     * @param limit
     * @param cloudId
     * @return
     */
    @RequestMapping(value = "/loadOrderPayInfo" , method = RequestMethod.POST)
    @ResponseBody
    public ResultData loadOrderPayInfo(String startTime,String endTime , String start , String limit , String cloudId){

        ResultData result = orderService.loadOrderPayInfoByTime(startTime,endTime,start,limit,cloudId);
        return result;
    }

    /**
     * 根据订单号查找支付信息
     * @param orderNo
     * @return
     */
    @RequestMapping(value = "/loadOrderPayByOrderNo" ,method = RequestMethod.POST)
    @ResponseBody
    public ResultData loadOrderPayInfoByOrderNo(String orderNo){

        ResultData result = orderService.loadOrderPayInfoByOrderNo(orderNo);
        return result;
    }


    @RequestMapping(value = "/loadTotalMoneyOfPerBranch" ,method = RequestMethod.POST)
    @ResponseBody
    public ResultData loadTotalMoneyOfPerBranch(String startTime,String endTime , String start , String limit , String cloudId){
        ResultData result = new ResultData();
        return result;
    }

    /**
     *  日流水线形图
     * @param startTime
     * @param endTime
     * @param cloudId
     * @return
     */
    @RequestMapping(value = "loadDayAmount" , method = RequestMethod.POST)
    @ResponseBody
    public ResultData loadDayAmount(String startTime ,String endTime , String cloudId){
        ResultData result = orderService.loadDayAmount(startTime,endTime,cloudId);
        return result ;
    }

    /**
     * 月流水柱状图
     * @param year
     * @param cloudId
     * @return
     */
    @RequestMapping(value = "loadMonthAmount" , method = RequestMethod.POST)
    @ResponseBody
    public ResultData loadMonthAmount(String year , String cloudId){

        ResultData result = orderService.loadMonthAmount(year , cloudId);

        return  result ;
    }

    @RequestMapping(value = "loadDayOrderCount")
    @ResponseBody
    public ResultData loadOrderCount(){
        return null ;
    }

    /**
     * 统计所有分店销售额
     * @param month
     * @param userId
     * @return
     */
    @RequestMapping(value = "loadAccountInfo" , method = RequestMethod.POST)
    @ResponseBody
    public ResultData loadAccountInfo(String month , String userId){

        ResultData result = orderService.loadAccountInfo(month , userId);
        return result ;
    }

    /**
     * 统计菜品类别销售额
     * @param cloudId
     * @return
     */
    @RequestMapping(value = "loadAmountByFoodType" , method = RequestMethod.POST)
    @ResponseBody
    public ResultData loadAmountByFoodType(String cloudId){

        ResultData result = orderService.loadAmountByFoodType(cloudId);
        return result ;
    }

    /**
     * 统计订单数量
     * @param startTime
     * @param endTime
     * @param cloudId
     * @return
     */
    @RequestMapping(value = "loadOrderCountOfDay" , method = RequestMethod.POST)
    @ResponseBody
    public ResultData loadOrderCountOfDay(String startTime , String endTime , String cloudId){

        ResultData result = orderService.loadOrderCountOfDay(startTime , endTime , cloudId);
        return result ;
    }

    /***
     * 统计月销售总额
     * @param year
     * @param cloudId
     * @return
     */
    @RequestMapping(value = "loadMonthAmountAmount" ,method = RequestMethod.POST)
    @ResponseBody
    public ResultData loadMonthAmountAmount(String year , String cloudId){
        ResultData result = orderService.loadMonthAmountAmount( year ,  cloudId);

        return result;
    }

    /**
     * 统计菜品销量
     * @param cloudId
     * @param limit
     * @return
     */
    @RequestMapping(value = "loadFoodSellInfo" , method = RequestMethod.POST)
    @ResponseBody
    public ResultData loadFoodSellInfo(String cloudId , String limit){

        ResultData result = orderService.loadFoodSellInfo(cloudId, limit);
        return result ;
    }

    /**
     * 统计客流量
     * @param cloudId
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "loadFootFall" , method = RequestMethod.POST)
    @ResponseBody
    public ResultData loadFootFall(String cloudId , String startTime , String endTime){

        ResultData result = orderService.loadFootFall(cloudId , startTime , endTime);
        return result ;
    }

    /**
     * 统计平均消费
     * @param cloudId
     * @return
     */
    @RequestMapping(value = "loadAvgCost" , method = RequestMethod.POST)
    @ResponseBody
    public ResultData loadAvgCost(String cloudId){
        ResultData result = orderService.loadAvgCost(cloudId);
        return result ;
    }

    /**
     * 统计回头率
     * @param cloudId
     * @param month
     * @return
     */
    @RequestMapping(value = "loadSecondCosts" , method = RequestMethod.POST)
    @ResponseBody
    public ResultData loadSecondCosts(String cloudId , String month){

        ResultData result = orderService.loadSecondCosts(cloudId , month);
        return result ;
    }


    @RequestMapping(value = "loadTableTurnoverRate" , method = RequestMethod.POST)
    @ResponseBody
    public ResultData loadTableTurnoverRate(String cloudId , String startTime , String endTime){

        ResultData result = orderService.loadTableTurnoverRate( cloudId , startTime , endTime ) ;
        return result ;
    }


}
