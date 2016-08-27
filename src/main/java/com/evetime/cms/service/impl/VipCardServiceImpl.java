package com.evetime.cms.service.impl;


import com.evetime.cms.dao.VipCardDao;
import com.evetime.cms.entity.ResultData;
import com.evetime.cms.entity.VipCard;
import com.evetime.cms.service.VipCardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Rains
 * on 2016-06-23.
 */
@Service("VipCardService")
public class VipCardServiceImpl implements VipCardService {

    @Resource(name = "VipCardDao")
    private VipCardDao vipCardDao;

    @Override
    public ResultData loadAll(String cloudId) {
        ResultData result = new ResultData();

        List<VipCard> vipCardList = vipCardDao.queryAll(cloudId);

        result.setSuccess(true);
        result.setMessage("加载成功");
        result.setData(vipCardList);
        return result;
    }
}
