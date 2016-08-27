package com.evetime.app.dao;

import com.evetime.app.entity.Brand;

import java.util.List;
import java.util.Map;

/**
 * Created by Rains
 * on 2016-07-04.
 */
public interface BrandDao {

    /**
     *
     * 查询品牌信息
     * @param userId 用户Id
     * @return
     */
    Brand queryByUserId(String userId);


}
