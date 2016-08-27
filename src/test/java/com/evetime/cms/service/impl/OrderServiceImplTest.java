package com.evetime.cms.service.impl;

import com.evetime.cms.dao.OrderDao;
import com.evetime.cms.entity.ResultData;
import com.evetime.cms.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Rains
 * on 2016-06-27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/springmvc-config.xml")
public class OrderServiceImplTest {

    @Resource(name = "OrderService")
    private OrderService orderService;

    @Test
    public void testLoadFootFall() throws Exception {
        ResultData result = orderService.loadFootFall("5fda747e-e014-4108-8406-79abb1f657ed" , "2016-06-11" , "2016-06-20");
        System.out.println(result);

    }
}