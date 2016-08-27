package com.evetime.cms.dao.impl;

import com.evetime.cms.dao.OrderDao;
import com.evetime.cms.entity.PayOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;


/**
 * Created by Rains
 * on 2016-06-20.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/springmvc-config.xml")
public class OrderDaoImplByTemplateTest {

    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderDao orderDao;

    @Test
        public void testFindPayInfoByTime() throws Exception {
        List<PayOrder> payInfoByTime = orderDao.findPayInfoByTime("2016-01-01 00:00:00", "2016-06-01 00:00:00", "0", "15", "5fda747e-e014-4108-8406-79abb1f657ed");
        logger.info("result:{}" , payInfoByTime);
        logger.debug("debug{}",payInfoByTime);
    }

    @Test
    public void testFindCountOfPayInfo() throws Exception {

    }

    @Test
    public void testFindPayOrderByOrderNo() throws Exception {

    }

    @Test
    public void testFindDayAmount() throws Exception {

    }

    @Test
    public void testFindMonthAmountAmount() throws Exception {

    }

    @Test
    public void testFindOrderCountOfDay() throws Exception {

    }

    @Test
    public void testFindAccountInfo() throws Exception {

    }

    @Test
    public void testFindAccountInfo1() throws Exception {
        List<Map<String, Object>> accountInfo = orderDao.findAccountInfo("06", "4c50686e-ada4-4717-9102-839360fca1a7");
        logger.info("list {}" ,accountInfo);
    }

    @Test
    public void testFindAmountByFoodType() throws Exception {

    }

    @Test
    public void testFindFoodSellInfo() throws Exception {

    }

    @Test
    public void testFindFootFall() throws Exception {
        List<Map<String, Object>> footFall = orderDao.findFootFall("5fda747e-e014-4108-8406-79abb1f657ed", "2016-06-17", "2016-06-20");
        logger.info("List :{}" , footFall);
    }

    @Test
    public void testFindAvgCost() throws Exception {
        List<Map<String, Object>> avgCost = orderDao.findAvgCost("5fda747e-e014-4108-8406-79abb1f657ed");
        logger.info("map : {}" , avgCost);

    }

    @Test
    public void testFindCostCount() throws Exception {
        List<Map<String, Object>> costCount = orderDao.findCostCount("5fda747e-e014-4108-8406-79abb1f657ed", "2016-06");
        logger.info("map:{}" , costCount);
    }

    @Test
    public void testFindTableTurnoverRate() throws Exception {

        List<Map<String, Object>> tableTurnoverRate = orderDao.findTableTurnoverRate("5fda747e-e014-4108-8406-79abb1f657ed", "2016-02-25", "2016-06-10");
        logger.info("list {}" , tableTurnoverRate);


    }

}