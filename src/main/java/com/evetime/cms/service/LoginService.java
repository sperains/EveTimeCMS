package com.evetime.cms.service;

import com.evetime.cms.entity.ResultData;
import com.evetime.cms.entity.User;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016-04-18.
 */
public interface LoginService {

    ResultData checkLogin(User user);

    ResultData checkSession(HttpSession session);
}
