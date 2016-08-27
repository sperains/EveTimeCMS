package com.evetime.app.dao.impl;

import com.evetime.app.dao.BranchSaleDataDao;
import com.evetime.app.dao.BrandDao;
import com.evetime.app.dao.BrandSaleDateDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

/**
 * Created by Rains
 * on 2016-07-05.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/springmvc-config.xml")
public class BranchSaleDataDaoImplTest {

    @Resource(name = "AppBrandDao")
    BrandDao brandDao ;

    @Resource(name = "AppBranchSaleDataDao")
    BranchSaleDataDao branchSaleDataDao;

    @Resource(name = "AppBrandSaleDataDao")
    BrandSaleDateDao brandSaleDataDao;

    @Test
    public void testQueryBrandWeekSale() throws Exception {
        List<Map<String, Object>> dddd = brandSaleDataDao.querySaleOfWeek("dddd");
        System.out.println(dddd);

    }

    @Test
    public void testQueryTodayOrderCount() throws Exception {
        int i = branchSaleDataDao.queryTodayOrderCount("5fda747e-e014-4108-8406-79abb1f657ed");
        System.out.println(i);

    }

    @Test
    public void testQueryYesterdayOrderCount() throws Exception {
        int i = branchSaleDataDao.queryYesterdayOrderCount("5fda747e-e014-4108-8406-79abb1f657ed");
        System.out.println(i);
    }

    @Test
    public void testQueryTodayFoodSellCount() throws Exception {
        int i =  branchSaleDataDao.queryTodayFoodSellCount("5fda747e-e014-4108-8406-79abb1f657edf");
        System.out.println(i);
    }

    @Test
    public void testQueryYesterDayFoodSellCount() throws Exception {
        int i = branchSaleDataDao.queryYesterDayFoodSellCount("5fda747e-e014-4108-8406-79abb1f657ed");
        System.out.println(i);
    }

    @Test
    public void testQueryTodayAverageCost() throws Exception {
        double v = branchSaleDataDao.queryTodayAverageCost("5fda747e-e014-4108-8406-79abb1f657ed");
        System.out.println(v);

    }

    @Test
    public void testQueryYesterDayAverageCost() throws Exception {
        double v = branchSaleDataDao.queryYesterDayAverageCost("5fda747e-e014-4108-8406-79abb1f657ed");
        System.out.println(v);
    }

    @Test
    public void testQueryTodayTableTurnOverRate() throws Exception {
        double v = branchSaleDataDao.queryTodayTableTurnOverRate("5fda747e-e014-4108-8406-79abb1f657edS");
        System.out.println(v);
    }

    @Test
    public void testQueryYesterDayTableTurnOverRate() throws Exception {
        double v = branchSaleDataDao.queryYesterDayTableTurnOverRate("5fda747e-e014-4108-8406-79abb1f657edS");
        System.out.println(v);
    }

    @Test
    public void testQueryWeekSale() throws Exception {
        List<Map<String, Object>> maps = branchSaleDataDao.queryWeekSale("5fda747e-e014-4108-8406-79abb1f657ed");
        System.out.println(maps);
    }

    @Test
    public void testQueryFoodSellInfo() throws Exception {
        List<Map<String, Object>> todaySellInfo = branchSaleDataDao.queryFoodSellInfo("5fda747e-e014-4108-8406-79abb1f657ed2", 0);
        List<Map<String, Object>> weekSellInfo = branchSaleDataDao.queryFoodSellInfo("5fda747e-e014-4108-8406-79abb1f657ed", 1);
        List<Map<String, Object>> monthSellInfo = branchSaleDataDao.queryFoodSellInfo("5fda747e-e014-4108-8406-79abb1f657ed", 2);
        List<Map<String, Object>> yearSellInfo = branchSaleDataDao.queryFoodSellInfo("5fda747e-e014-4108-8406-79abb1f657ed", 3);

        System.out.println(todaySellInfo);
        System.out.println(weekSellInfo);
        System.out.println(monthSellInfo);
        System.out.println(yearSellInfo);
    }
}