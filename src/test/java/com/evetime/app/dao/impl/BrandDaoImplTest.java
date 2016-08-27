package com.evetime.app.dao.impl;

import com.evetime.app.dao.BrandDao;
import com.evetime.app.entity.Brand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Rains
 * on 2016-07-04.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/springmvc-config.xml")
public class BrandDaoImplTest {

    @Resource(name = "AppBrandDao")
    private BrandDao brandDao ;

    @Test
    public void testQueryByUserId() throws Exception {
        Brand dddd = brandDao.queryByUserId("dddd");
        System.out.println(dddd);
    }
}