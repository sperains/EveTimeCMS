package com.evetime.app.dao.impl;

import com.evetime.app.dao.BrandSaleDateDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Rains
 * on 2016-07-09.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/springmvc-config.xml")
public class BrandSaleDataDaoImplTest {

    @Resource(name = "AppBrandSaleDataDao")
    private BrandSaleDateDao brandSaleDateDao;

    @Test
    public void testQuerySaleOfWeek() throws Exception {

    }

    @Test
    public void testQueryCurWeekSale() throws Exception {

    }

    @Test
    public void testQueryCurMonthSale() throws Exception {

    }

    @Test
    public void testQueryCurYearSale() throws Exception {

    }

    @Test
    public void testQueryDayOfMonthSale() throws Exception {
        List<Map<String, Object>> dddd = brandSaleDateDao.queryMonthSale("dddd", 3);
        System.out.println(dddd);

    }


    @Test
    public void testQueryMonthSale() throws Exception {

    }
}