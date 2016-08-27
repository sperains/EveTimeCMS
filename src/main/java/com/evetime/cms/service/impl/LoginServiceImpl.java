package com.evetime.cms.service.impl;

import com.evetime.cms.dao.LoginDao;
import com.evetime.cms.entity.ResultData;
import com.evetime.cms.entity.User;
import com.evetime.cms.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2016-04-18.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginDao loginDao;

    public LoginDao getLoginDao() {
        return loginDao;
    }

    public void setLoginDao(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    /**

     */
    public ResultData checkLogin(User user) {
        //System.out.println(user.toString());
        ResultData result = new ResultData();

        if(user.getUserName()==null || user.getPassword()==null){
            result.setSuccess(false);
            result.setMessage("请求参数有误");
            return result;
        }


        List<User> list = loginDao.findUserByUserName(user.getUserName());

        if(list.size() == 0){
            result.setSuccess(false);
            result.setMessage("用户名不存在");
            return result;
        }

        User u = list.get(0);
        if(!u.getPassword().equals(user.getPassword())){
            result.setSuccess(false);
            result.setMessage("密码错误");
            return result;
        }

        result.setSuccess(true);
        result.setMessage("验证成功");
        result.setData(user.getUserName());
        return result;

    }


    public ResultData checkSession(HttpSession session) {

        ResultData result = new ResultData();
        Object userName = session.getAttribute("userName");
        if(userName == null){
            result.setSuccess(false);
            result.setMessage("未登录");
            return result;
        }

        result.setSuccess(true);
        result.setMessage("已登录");
        return result;
    }
}
