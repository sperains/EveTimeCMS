package com.evetime.cms.controller;

import com.evetime.cms.entity.ResultData;
import com.evetime.cms.service.WithdrawCashService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016-04-25.
 */
@Controller
public class WithdrawCash {

    @Resource
    private WithdrawCashService withdrawCashService;

    public WithdrawCashService getWithdrawCashService() {
        return withdrawCashService;
    }

    public void setWithdrawCashService(WithdrawCashService withdrawCashService) {
        this.withdrawCashService = withdrawCashService;
    }

    @RequestMapping(value = "loadApplyOrder" , method = RequestMethod.GET)
    @ResponseBody
    public ResultData load(){
       ResultData result =  withdrawCashService.loadAllApplyInfo();
        return result;
    }
}
