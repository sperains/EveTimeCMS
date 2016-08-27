package com.evetime.cms.service;

import com.evetime.cms.entity.ResultData;
import com.evetime.cms.entity.Vip;

/**
 * Created by Rains
 * on 2016-06-22.
 */
public interface VipService {

    ResultData loadAll(String userId);

    ResultData add(Vip vip);

    ResultData delete(String vipId);

    ResultData update(Vip vip);
}
