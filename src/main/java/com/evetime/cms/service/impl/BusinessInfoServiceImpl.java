package com.evetime.cms.service.impl;

import com.evetime.cms.dao.BusinessFileDao;
import com.evetime.cms.dao.BusinessInfoDao;
import com.evetime.cms.entity.BusinessFile;
import com.evetime.cms.entity.BusinessInfo;
import com.evetime.cms.entity.ResultData;
import com.evetime.cms.service.BusinessInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Rains
 * on 2016-07-26.
 */
@Service("BusinessInfoService")
public class BusinessInfoServiceImpl implements BusinessInfoService {

    private Logger logger = LoggerFactory.getLogger(BusinessInfoService.class);

    @Resource(name = "BusinessInfoDao")
    private BusinessInfoDao businessInfoDao;

    @Resource(name = "BusinessFileDao")
    private BusinessFileDao businessFileDao;

    public BusinessInfoDao getBusinessInfoDao() {
        return businessInfoDao;
    }

    public void setBusinessInfoDao(BusinessInfoDao businessInfoDao) {
        this.businessInfoDao = businessInfoDao;
    }

    public BusinessFileDao getBusinessFileDao() {
        return businessFileDao;
    }

    public void setBusinessFileDao(BusinessFileDao businessFileDao) {
        this.businessFileDao = businessFileDao;
    }

    @Override
    @Transactional
    public ResultData addInformation(BusinessInfo businessInfo, List<BusinessFile> businessFileList) {

        ResultData result = new ResultData();

        try {
            int m = businessInfoDao.insert(businessInfo);

            int n = businessFileDao.insert(businessFileList);
        } catch (Exception e) {
            logger.error(e.getMessage() , e);
            return new ResultData(false , "插入数据失败");
        }

        result.setSuccess(true);
        result.setMessage("添加数据成功");
        return result ;
    }

    @Override
    public ResultData findAllInformations() {

        ResultData result = new ResultData();

        List<BusinessInfo> businessInfos = null;
        try {
            businessInfos = businessInfoDao.queryAll();
        } catch (Exception e) {
            logger.error(e.getMessage() , e);
            return new ResultData(false , "请稍后再试");
        }

        result.setSuccess(true);
        result.setMessage("加载成功");
        result.setData(businessInfos);

        return result;
    }

    /**
     * 传统方式,通过response下载文件
     * @param id
     * @param response
     */
    @Override
    public void fileDownLoad(String id, HttpServletResponse response) {

        BusinessFile businessFile = businessFileDao.queryById(id);


        InputStream in  = null ;
        OutputStream out = null;

        try {

            response.setContentType("application/octet-stream; charset=utf-8");

            response.setHeader("Content-Disposition", "attachment; filename=" + new String(businessFile.getFileName().getBytes("UTF-8"), "ISO8859-1"));


            byte[] buff = new byte[1024] ;
            int d ;
            in = businessFile.getContent();
            out = response.getOutputStream();

            while( (d = in.read(buff)) != -1){
                out.write(buff , 0 , d);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public ResponseEntity fileDownLoad(String id) {

        //根据文件id获取文件对象
        BusinessFile file = businessFileDao.queryById(id);

        String dfileName = null;
        try {
            //解决下载文件时中文不能显示的问题
            dfileName = new String(file.getFileName().getBytes("UTF-8"), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", dfileName);

        InputStream in = file.getContent();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        byte[] buff = new byte[1024];

        int d ;
        try {
            while( (d=in.read(buff)) != -1){
                out.write(buff , 0 , d );
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new ResponseEntity(out.toByteArray(), headers, HttpStatus.CREATED);
    }

    @Override
    public ResultData updateInformation(BusinessInfo businessInfo) {

        ResultData result = new ResultData();

        String id = businessInfo.getId();

        BusinessInfo businessInfo1 = businessInfoDao.queryById(businessInfo.getId());

        Field[] fieldList = BusinessInfo.class.getDeclaredFields();

        for(Field field : fieldList){
            try {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(businessInfo);

                if(value != null){
                    field.set(businessInfo1 , value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        int n = businessInfoDao.update(businessInfo1);
        result.setSuccess(true);
        result.setMessage("修改成功");

        return result;
    }

    @Override
    public ResultData updateInformation(BusinessInfo businessInfo, List<BusinessFile> businessFileList) {

        ResultData result = new ResultData();

        int i = updateBusinessInfo(businessInfo);
        int j = updateBusinessFile(businessFileList);

        result.setSuccess(true);
        result.setMessage("修改成功");

        return result;
    }

    @Override
    public ResultData loadAppendFile(String businessInfoId) {

        ResultData result = new ResultData();

        List<BusinessFile> businessFileList = businessFileDao.queryAppendFile(businessInfoId);

        result.setSuccess(true);
        result.setMessage("加载附件成功");
        result.setData(businessFileList);
        return result;
    }

    @Override
    public ResultData deleteInfo(String id) {

        ResultData result = new ResultData();

        try {
            int delete = businessInfoDao.delete(id);
        } catch (Exception e) {
            return new ResultData(false , "删除失败");
        }

        result.setSuccess(true);
        result.setMessage("删除成功");

        return result;
    }

    @Override
    public ResultData updateContractStatus(String id, int status) {

        ResultData result = new ResultData();

        try {
            int n = businessInfoDao.updateContractStatus(id , status);
        } catch (Exception e) {
            e.printStackTrace();
            return  new ResultData(false , "修改失败");
        }

        result.setSuccess(true);
        result.setMessage("修改成功");
        return result;
    }


    /**
        更新商户信息
     */
    private int updateBusinessInfo(BusinessInfo businessInfo){

        String id = businessInfo.getId();

        //取出数据库中对应id的数据
        BusinessInfo businessInfo1 = businessInfoDao.queryById(businessInfo.getId());

        //获取BusinessInfo中的所有字段
        Field[] fieldList = BusinessInfo.class.getDeclaredFields();

        //遍历字段,若前端传入的businessInfo中有存在数据的字段则覆盖businessInfo1中的数据
        for(Field field : fieldList){
            try {
                field.setAccessible(true);
                //获取字段名
                String fieldName = field.getName();
                //获取businessInfo中对应字段的值
                Object object = field.get(businessInfo);
                String value = (String) field.get(businessInfo);

                //若不为空,覆盖businessInfo1中的数据
                if(value != null){
                    field.set(businessInfo1 , value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        int n = businessInfoDao.update(businessInfo1);
        return n ;
    }


    private int updateBusinessFile(List<BusinessFile> businessFileList){

        int n = businessFileDao.update(businessFileList);

        return n ;
    }




}
