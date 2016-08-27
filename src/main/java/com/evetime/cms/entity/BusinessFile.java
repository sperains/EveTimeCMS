package com.evetime.cms.entity;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by Rains
 *
 * 商家文件
 * on 2016-07-25.
 */
public class BusinessFile {

    private String id;
    private String businessInfoId;
    private String fileName;
    private String fieldName;
    private InputStream content;
    private long fileSize;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusinessInfoId() {
        return businessInfoId;
    }

    public void setBusinessInfoId(String businessInfoId) {
        this.businessInfoId = businessInfoId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public InputStream getContent() {
        return content;
    }

    public void setContent(InputStream content) {
        this.content = content;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }


    @Override
    public String toString() {
        return "BusinessFile{" +
                "id='" + id + '\'' +
                ", businessInfoId='" + businessInfoId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", content=" + content +
                ", fileSize=" + fileSize +
                '}';
    }
}
