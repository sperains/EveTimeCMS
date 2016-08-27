package com.evetime.cms.service;

import com.evetime.cms.entity.ResultData;

/**
 * Created by Administrator on 2016-04-25.
 */
public interface WithdrawCashService {

    //获取所有的提现申请信息
    ResultData loadAllApplyInfo();
}
