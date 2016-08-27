package com.evetime.cms.dao;

import com.evetime.cms.entity.Menu;

import java.util.List;

/**
 * Created by Administrator on 2016-04-18.
 */
public interface MenuDao {

    List<Menu> findRootMenu(String userName);

    List<Menu> findMenusByParentId(int parentId, String userName);
}
