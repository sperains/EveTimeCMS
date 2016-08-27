package com.evetime.cms.dao;

import com.evetime.cms.entity.Vip;

import java.util.List;

/**
 * Created by Rains
 * on 2016-06-21.
 */
public interface VipDao {

    List<Vip> queryAll();

    List<Vip> queryByUserId(String userId);

    Vip queryById(String vipId);

    Vip queryByVipCode();

    int add(Vip vip);

    int delete(String vipId);

    int update(Vip vip);

}
