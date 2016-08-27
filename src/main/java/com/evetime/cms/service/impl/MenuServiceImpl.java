package com.evetime.cms.service.impl;


import com.evetime.cms.dao.MenuDao;
import com.evetime.cms.entity.Menu;
import com.evetime.cms.entity.ResultData;
import com.evetime.cms.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-04-18.
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuDao menuDao;

    public MenuDao getMenuDao() {
        return menuDao;
    }

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    public ResultData loadMenus(String userName) {

        ResultData result = new ResultData();

        List<Menu> list = menuDao.findRootMenu(userName);

        for(int i = 0 ; i < list.size() ; i++){
            Menu m = list.get(i);
            List<Menu> menus = menuDao.findMenusByParentId(m.getId() , userName);
            for(Menu l : menus){
                l.setLeaf(true);
            }
            m.setChildren(menus);
        }
        //System.out.println(list);
        result.setSuccess(true);
        result.setMessage("OK");
        result.setData(list);
        return result;
    }
}
