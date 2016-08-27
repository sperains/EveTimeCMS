package com.evetime.cms.dao;

import com.evetime.cms.entity.FoodInfo;

import java.util.List;

/**
 * Created by Administrator on 2016-08-16.
 */
public interface FoodInfoDao {

    List<FoodInfo> findAll(String cloudId);
}
