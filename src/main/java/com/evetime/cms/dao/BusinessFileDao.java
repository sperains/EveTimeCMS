package com.evetime.cms.dao;

import com.evetime.cms.entity.BusinessFile;

import java.util.List;

/**
 * Created by Rains
 * on 2016-07-25.
 */


public interface BusinessFileDao {

    int insert(BusinessFile businessFile);

    int insert(List<BusinessFile> businessFileList);

    BusinessFile queryByBusinessInfoIdAndFileName(String businessInfoId , String fileName);

    /**
     * 根据文件id查找商户文件对象
     * @param
     * @return
     */
    BusinessFile queryById(String id);

    //批量修改商户信息文件
    int update(List<BusinessFile> businessFileList);

    /**
     * 根据businessInfoId 查找 上传的商户附件
     * @param businessInfoId
     * @return
     */
    List<BusinessFile> queryAppendFile(String businessInfoId);



}
