package com.evetime.cms.service.impl;

import com.evetime.cms.dao.OrderDao;
import com.evetime.cms.entity.PayOrder;
import com.evetime.cms.entity.ResultData;
import com.evetime.cms.service.OrderService;
import org.apache.http.util.TextUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Rains
 * on 2016-04-21.
 */
@Service("OrderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public ResultData loadOrderPayInfoByTime(String startTime, String endTime, String start, String limit, String cloudId) {
        ResultData result = new ResultData();

        List<PayOrder> orderList = orderDao.findPayInfoByTime(startTime,endTime,start,limit,cloudId);

        if(orderList.size()== 0){
            result.setData("1");
            result.setMessage("没有查询到相关数据");
            return result;
        }

        //获取数据的总条数和总金额.
        Map<String, Object> data = orderDao.findCountOfPayInfo(startTime, endTime, cloudId);
        data.put("orders",orderList);
        result.setSuccess(true);
        result.setMessage("加载支付订单信息成功");
        result.setData(data);
        return result;
    }

    public ResultData loadOrderPayInfoByOrderNo(String orderNo) {
        ResultData result = new ResultData();

        PayOrder payOrder = orderDao.findPayOrderByOrderNo(orderNo);

        if(payOrder == null){
            result.setSuccess(false);
            result.setMessage("没有查询到相关记录.");
            return result;
        }

        result.setSuccess(true);
        result.setMessage("查询成功");
        result.setData(payOrder);
        return result;
    }

    @Override
    public ResultData loadDayAmount(String startTime, String endTime, String cloudId) {

        ResultData result = new ResultData();
        List<Map<String, String>> data = orderDao.findDayAmount(startTime, endTime, cloudId);

        if(data.size() == 0){
            result.setSuccess(false);
            result.setMessage("该时间段没有数据");
            return result;
        }

        result.setSuccess(true);
        result.setMessage("加载成功");
        result.setData(data);
        return result;
    }

    @Override
    public ResultData loadMonthAmount(String year, String cloudId) {

        ResultData result = new ResultData();
        List<Map<String, String>> data = orderDao.findMonthAmountAmount(year, cloudId);

        if(data.size()== 0){
            result.setSuccess(false);
            result.setMessage("该年没有数据");
            return result;
        }

        result.setSuccess(true);
        result.setMessage("加载成功");
        result.setData(data);
        return result;
    }

    @Override
    public ResultData loadAccountInfo(String month) {

        ResultData result = new ResultData();

        List<Map<String, Object>> accountInfo = orderDao.findAccountInfo(month);

        if(accountInfo.size() == 0){
            result.setSuccess(false);
            result.setMessage("当前时间段没有数据");
            return result;
        }

        result.setSuccess(true);
        result.setMessage("查询成功");
        result.setData(accountInfo);

        return result;
    }

    @Override
    public ResultData loadAccountInfo(String month, String userId) {

        ResultData result = new ResultData();

        List<Map<String, Object>> accountInfo = orderDao.findAccountInfo(month , userId);

        if(accountInfo.size() == 0){
            result.setSuccess(false);
            result.setMessage("当前时间段没有数据");
            return result;
        }

        result.setSuccess(true);
        result.setMessage("查询成功");
        result.setData(accountInfo);

        return result;
    }

    @Override
    public ResultData loadAmountByFoodType(String cloudId) {
        ResultData result = new ResultData();

        List<Map<String, Object>> data = orderDao.findAmountByFoodType(cloudId);

        if(data.size() == 0){
            result.setSuccess(false);
            result.setMessage("加载失败");
            return result;
        }

        result.setSuccess(true);
        result.setMessage("加载成功");
        result.setData(data);
        return result;
    }

    @Override
    public ResultData loadOrderCountOfDay(String startTime, String endTime, String cloudId) {

        ResultData result = new ResultData();

        List<Map<String, Object>> data = orderDao.findOrderCountOfDay(startTime, endTime, cloudId);

        if(data.size() == 0){
            result.setSuccess(false);
            result.setMessage("当前时间段没有数据");
            return result;
        }

        result.setSuccess(true);
        result.setMessage("加载成功");
        result.setData(data);
        return  result;
    }

    @Override
    public ResultData loadMonthAmountAmount(String year, String cloudId) {

        ResultData result = new ResultData();

        List<Map<String, String>> data = orderDao.findMonthAmountAmount(year, cloudId);
        if(data.size() == 0){
            result.setSuccess(false);
            result.setMessage("当前没有数据");
            return result;
        }

        result.setSuccess(true);
        result.setMessage("加载成功");
        result.setData(data);

        return result;
    }

    @Override
    public ResultData loadFoodSellInfo(String cloudId, String limit) {

        ResultData result = new ResultData();

        List<Map<String, Object>> data = orderDao.findFoodSellInfo(cloudId, limit);

        if(data.size()==0){
            result.setSuccess(false);
            result.setMessage("当前没有数据");
            return result;
        }

        result.setSuccess(true);
        result.setMessage("加载成功");
        result.setData(data);
        return result;
    }

    @Override
    public ResultData loadFootFall(String cloudId, String startTime, String endTime) {

        ResultData result = new ResultData();
        if(cloudId == null || "".equals(cloudId)){
            result.setSuccess(false);
            result.setMessage("cloudId不能为空");
            return result;
        }

        if(startTime == null || "".equals(startTime)){
            result.setSuccess(false);
            result.setMessage("起始时间不能为空");
            return result;
        }

        if(endTime == null || "".equals(endTime)){
            result.setSuccess(false);
            result.setMessage("截止时间不能为空");
            return result;
        }


        List<Map<String, Object>> footFall = orderDao.findFootFall(cloudId, startTime, endTime);

        if(footFall.size() == 0 ){
            result.setSuccess(false);
            result.setMessage("当前时间段没有数据");
            return result;
        }

        result.setSuccess(true);
        result.setMessage("加载成功");
        result.setData(footFall);


        return result;
    }

    @Override
    public ResultData loadAvgCost(String cloudId) {

        ResultData result = new ResultData();

        if(cloudId == null || "".equals(cloudId)){
            result.setSuccess(false);
            result.setMessage("cloudId不能为空");
            return result ;
        }

        List<Map<String, Object>> avgCost = orderDao.findAvgCost(cloudId);
        if(avgCost.size() == 0){
            result.setSuccess(false);
            result.setMessage("当前没有数据");
            return result ;
        }

        result.setSuccess(true);
        result.setMessage("加载成功");
        result.setData(avgCost);
        return result;
    }

    @Override
    public ResultData loadSecondCosts(String cloudId, String month) {

        ResultData result = new ResultData();
        if(cloudId == null || "".equals(cloudId)){
            result.setSuccess(false);
            result.setMessage("CloudId不能为空");
            return result;
        }

        if(month == null || "".equals(month)){
            result.setSuccess(false);
            result.setMessage("月份不能为空");
            return result;
        }

        List<Map<String, Object>> secondCost = orderDao.findCostCount(cloudId, month);


        result.setSuccess(true);
        result.setMessage("加载成功");
        result.setData(secondCost);

        return result;
    }

    @Override
    public ResultData loadTableTurnoverRate(String cloudId, String startTime, String endTime) {

        ResultData result = new ResultData();
        if(cloudId == null || "".equals(cloudId)){
            result.setSuccess(false);
            result.setMessage("CloudId不能为空");
            return result;
        }

        if(startTime == null || "".equals(startTime) ){
            result.setSuccess(false);
            result.setMessage("startTime不能为空");
            return result;
        }

        if(endTime == null || "".equals(endTime)){
            result.setSuccess(false);
            result.setMessage("endTime不能为空");
            return result;
        }

        List<Map<String, Object>> tableTurnoverRateList = orderDao.findTableTurnoverRate(cloudId, startTime, endTime);

        if(tableTurnoverRateList.size() == 0){
            result.setSuccess(false);
            result.setMessage("当前时段没有数据");
            return result;
        }

        result.setSuccess(true);
        result.setMessage("加载成功");
        result.setData(tableTurnoverRateList);

        return result;
    }
}
