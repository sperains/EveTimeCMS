package com.evetime.app.dao.impl;

import com.evetime.app.dao.UserDao;
import com.evetime.app.entity.User;
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
public class UserDaoImplTest {

    @Resource(name = "AppUserDao")
    private UserDao userDao;

    @Test
    public void testQueryByUserName() throws Exception {
        User admin = userDao.query("admin", "123");
        System.out.println(admin);

    }
}