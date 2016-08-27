package com.evetime.app.dao;

import com.evetime.app.entity.Branch;

import java.util.List;

/**
 * Created by Rains
 * on 2016-07-04.
 */
public interface BranchDao {

    List<Branch> queryByUserId(String userId);

}
