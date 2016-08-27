package com.evetime.cms.dao;

import com.evetime.cms.entity.Vip;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Rains
 * on 2016-06-22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/springmvc-config.xml")
public class VipDaoImplByTemplateTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VipDao vipDao;

    @Test
    public void testQueryAll() throws Exception {
        List<Vip> vips = vipDao.queryAll();
        logger.info("vips : {}" , vips);
    }

    @Test
    public void testQueryByUserId() throws Exception {

        List<Vip> vips = vipDao.queryByUserId("dddd");
        logger.info("vips : {}" , vips);

    }

    @Test
    public void testQueryById() throws Exception {

    }

    @Test
    public void testQueryByVipCode() throws Exception {

    }

    @Test
    public void testAdd() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }
}