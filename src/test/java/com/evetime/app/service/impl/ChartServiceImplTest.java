package com.evetime.app.service.impl;

import com.evetime.app.dto.ResultData;
import com.evetime.app.service.ChartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Rains
 * on 2016-07-05.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/springmvc-config.xml")
public class ChartServiceImplTest {

    @Resource(name = "AppChartService")
    private ChartService chartService;

    @Test
    public void testLoadBrandInfo() throws Exception {
        ResultData brandInfo = chartService.loadBrandInfo("admin", "123");
        System.out.println(brandInfo);

    }


    @Test
    public void testLoadBranchInfo() throws Exception {
        ResultData result = chartService.loadBranchInfo("5fda747e-e014-4108-8406-79abb1f657ed");
        System.out.println(result);
    }

    @Test
    public void testLoadBrandInfo1() throws Exception {

    }

    @Test
    public void testLoadBranchInfo1() throws Exception {

    }

    @Test
    public void testLoadFoodSellInfo() throws Exception {

    }

    @Test
    public void testLoadBrandSaleDetail() throws Exception {
        ResultData dddd = chartService.loadBrandSaleDetail("dddd", 2);
        System.out.println(dddd);
    }

    @Test
    public void testLoadBranchList() throws Exception {
        ResultData dddd = chartService.loadBranchList("dddd", 0);
        System.out.println(dddd);

    }
}