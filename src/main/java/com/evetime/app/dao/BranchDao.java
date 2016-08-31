package com.evetime.app.dao;

import com.evetime.app.entity.Branch;

import java.util.List;

/**
 * Created by Rains
 * on 2016-07-04.
 */
public interface BranchDao {

    /**
     * 查询分店相关信息
     * cloudId
     * 分店名称
     *
     * @param userId
     * @return
     */
    List<Branch> queryByUserId(String userId);

}
