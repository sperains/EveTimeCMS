package com.evetime.cms.entity;

/**
 * Created by Rains
 * on 2016-04-28.
 */
public class Brand {

    private String brandId;
    private String brandName;
    private String imgPath;
    private String createTime;
    private int delFlag;

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "brandId='" + brandId + '\'' +
                ", brandName='" + brandName + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", createTime='" + createTime + '\'' +
                ", delFlag=" + delFlag +
                '}';
    }
}
