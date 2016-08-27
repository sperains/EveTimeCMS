package com.evetime.cms.service;

import com.evetime.cms.entity.BusinessFile;
import com.evetime.cms.entity.BusinessInfo;
import com.evetime.cms.entity.ResultData;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Rains
 * on 2016-07-26.
 * 处理商户信息逻辑
 */
public interface BusinessInfoService {

    /**
     * 添加商户信息,商户文件资料
     * @param businessInfo
     * @param businessFileList
     * @return
     */
    ResultData addInformation(BusinessInfo businessInfo , List<BusinessFile> businessFileList);

    ResultData findAllInformations();

    void fileDownLoad(String id , HttpServletResponse response);

    ResponseEntity fileDownLoad(String id);

    ResultData updateInformation(BusinessInfo businessInfo);

    ResultData updateInformation(BusinessInfo businessInfo , List<BusinessFile> businessFileList);

    /**
     * 加载商户附件信息
     * @param businessInfoId
     * @return
     */
    ResultData loadAppendFile(String businessInfoId);

    /**
     * 删除商户信息
     * @param id
     * @return
     */
    ResultData deleteInfo(String id);

    /**
     * 更新商户合约状态
     * @param id
     * @param status
     * @return
     */
    ResultData updateContractStatus(String id , int status);

}
