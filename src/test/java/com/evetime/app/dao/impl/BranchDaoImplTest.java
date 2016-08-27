package com.evetime.app.dao.impl;

import com.evetime.app.dao.BranchDao;
import com.evetime.app.entity.Branch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by Rains
 * on 2016-07-04.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/springmvc-config.xml")
public class BranchDaoImplTest {

    @Resource(name = "AppBranchDao")
    private BranchDao branchDao;

    @Test
    public void testQueryByUserId() throws Exception {
        List<Branch> dddd = branchDao.queryByUserId("dddd");
        System.out.println(dddd);
    }
}