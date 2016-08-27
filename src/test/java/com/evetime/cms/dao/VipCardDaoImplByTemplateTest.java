package com.evetime.cms.dao;

import com.evetime.cms.entity.VipCard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Rains
 * on 2016-06-23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/springmvc-config.xml")
public class VipCardDaoImplByTemplateTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "VipCardDao")
    private VipCardDao vipCardDao;

    @Test
    public void testAdd() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testQueryAll() throws Exception {
        List<VipCard> vipCardList = vipCardDao.queryAll("5fda747e-e014-4108-8406-79abb1f657ed");
        logger.info("vipCardList:{}" , vipCardList);
    }
}