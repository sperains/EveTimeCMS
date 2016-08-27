package com.evetime.cms.dao;

import com.evetime.cms.entity.User;

import java.util.List;

/**
 * Created by Rains
 * on 2016-04-18.
 */
public interface LoginDao {
    List<User> findUserByUserName(String userName);
}
