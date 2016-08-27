package com.evetime.cms.controller;

import com.evetime.cms.entity.ResultData;
import com.evetime.cms.entity.User;
import com.evetime.cms.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016-04-18.
 */
@Controller
public class Login {

    @Resource
    private LoginService loginService;

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 登录验证
     * @param
     * @return result
     */
    /*@RequestMapping(value = "login" , method = RequestMethod.POST)
    @ResponseBody
    public ResultData login(User user, HttpSession session){
        ResultData result = loginService.checkLogin(user);

        if(result.isSuccess()){
            session.setAttribute("userName",user.getUserName());
            session.setMaxInactiveInterval(30*60);
        }
        return result;
    }*/

    @RequestMapping(value = "checkSession" , method = RequestMethod.GET)
    @ResponseBody
    public ResultData checkSession(HttpSession session){
        System.out.println("session controller");
        System.out.println(session);
        ResultData result = loginService.checkSession(session);
        return result;
    }


    @RequestMapping(value = "logout" , method = RequestMethod.GET)
    @ResponseBody
    public ResultData logout(HttpSession session){
        System.out.println("logout controller");
        session.removeAttribute("userName");
        ResultData result = new ResultData();
        result.setMessage("注销成功");
        result.setSuccess(true);
        return result ;
    }
}
