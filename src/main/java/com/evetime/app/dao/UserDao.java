package com.evetime.app.dao;

import com.evetime.app.entity.User;

/**
 * Created by Rains
 * on 2016-07-04.
 */
public interface UserDao {

    User query(String userName , String password);
}
