package com.evetime.cms.dao;

import com.evetime.cms.entity.BusinessInfo;

import java.util.List;

/**
 * Created by Rains
 * on 2016-07-25.
 */
public interface BusinessInfoDao {

    int insert(BusinessInfo businessInfo);

    List<BusinessInfo> queryAll();

    int update(BusinessInfo businessInfo);

    BusinessInfo queryById(String id);

    int delete(String id);

    /**
     * 修改商户合约信息状态 , 0 未提交, 1 已提交 , 2 已审核, 3 未审核
     * @param id
     * @param status
     * @return
     */
    int updateContractStatus(String id , int status);

}
