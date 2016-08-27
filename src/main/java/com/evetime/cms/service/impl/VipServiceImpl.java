package com.evetime.cms.service.impl;

import com.evetime.cms.dao.VipDao;
import com.evetime.cms.entity.ResultData;
import com.evetime.cms.entity.Vip;
import com.evetime.cms.service.VipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rains
 * on 2016-06-22.
 */
@Service("VipService")
public class VipServiceImpl implements VipService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());



    @Resource(name = "VipDao")
    private VipDao vipDao;


    @Override
    public ResultData loadAll(String userId) {

        ResultData result = new ResultData();

        List<Vip> vipList ;

        if(userId == null || "".equals(userId)){
            vipList = vipDao.queryAll();
        }else{
            vipList = vipDao.queryByUserId(userId);
        }


        if(vipList.size() == 0){
            result.setSuccess(false);
            result.setMessage("没有会员信息");
            return result;
        }

        result.setSuccess(true);
        result.setMessage("查询成功");
        result.setData(vipList);
        return result;
    }

    @Override
    public ResultData add(Vip vip) {
        return null;
    }

    @Override
    public ResultData delete(String vipId) {

        ResultData result = new ResultData();

        int i = vipDao.delete(vipId);

        if( i == 0 ){
            result.setSuccess(false);
            result.setMessage("删除失败");
            return result;
        }

        result.setSuccess(true);
        result.setMessage("删除成功");
        return result;
    }

    @Override
    public ResultData update(Vip vip) {

        ResultData result = new ResultData();

        int n = vipDao.update(vip);

        if(n == 0 ){
            result.setSuccess(false);
            result.setMessage("修改失败");
            return result ;
        }

        result.setSuccess(true);
        result.setMessage("修改成功");
        return result;
    }
}
