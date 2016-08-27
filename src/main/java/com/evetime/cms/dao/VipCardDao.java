package com.evetime.cms.dao;

import com.evetime.cms.entity.VipCard;

import java.util.List;

/**
 * Created by Rains
 * on 2016-06-21.
 */
public interface VipCardDao {

    int add(VipCard vipCard);

    int update(VipCard vipCard);

    int delete(String vipCardId);

    List<VipCard> queryAll(String cloudId);
}
