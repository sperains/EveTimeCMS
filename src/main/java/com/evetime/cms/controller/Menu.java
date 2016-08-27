package com.evetime.cms.controller;

import com.evetime.cms.entity.ResultData;
import com.evetime.cms.service.MenuService;
import com.mysql.jdbc.log.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by Rains on 2016-04-18.
 *
 */

@Controller
public class Menu {


    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MenuService menuService;

    public MenuService getMenuService() {
        return menuService;
    }

    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @RequestMapping(value = "/loadMenu" ,method = RequestMethod.POST)
    @ResponseBody
    public ResultData loadMenu(String userName){
        ResultData result =menuService.loadMenus(userName);
        return result;
    }
}
