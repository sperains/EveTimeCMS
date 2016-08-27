package com.evetime.cms.service.impl;

import com.evetime.cms.dao.WithdrawCashDao;
import com.evetime.cms.entity.ResultData;
import com.evetime.cms.entity.WithdrawCash;
import com.evetime.cms.service.WithdrawCashService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016-04-25.
 */

@Service
public class WithdrawCashServiceImpl implements WithdrawCashService {

    @Resource
    private WithdrawCashDao withdrawCashDao;

    public WithdrawCashDao getWithdrawCashDao() {
        return withdrawCashDao;
    }

    public void setWithdrawCashDao(WithdrawCashDao withdrawCashDao) {
        this.withdrawCashDao = withdrawCashDao;
    }

    public ResultData loadAllApplyInfo() {
        ResultData result = new ResultData();
        List<WithdrawCash> applyOrderList = withdrawCashDao.findApplyOrder();


        if(applyOrderList.size() == 0){
            result.setSuccess(false);
            result.setMessage("没有提现申请记录");
            return result;
        }


        result.setSuccess(true);
        result.setData(applyOrderList);
        result.setMessage("加载提现申请记录成功");
        return result;
    }
}
