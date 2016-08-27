package com.evetime.cms.dao;

import com.evetime.cms.entity.WithdrawCash;

import java.util.List;

/**
 * Created by Administrator on 2016-04-25.
 */
public interface WithdrawCashDao {


    //查找申请提现的订单
    List<WithdrawCash> findApplyOrder();
}
