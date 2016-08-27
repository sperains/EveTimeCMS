package com.evetime.cms.service.impl;

import com.evetime.cms.entity.ResultData;
import com.evetime.cms.service.VipService;
import com.mysql.jdbc.log.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Rains
 * on 2016-06-22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/springmvc-config.xml")
public class VipServiceImplTest {

    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "VipService")
    private VipService vipService;

    @Test
    public void testLoadAll() throws Exception {
        try {
            ResultData vipList = vipService.loadAll("dddd");
            logger.info("vipList:{}" , vipList);
        } catch (Exception e) {
            logger.info(e.getMessage() , e);
        }
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