package com.evetime.cms.entity;

import java.util.Date;

/**
 * Created by Rains
 * on 2016-06-21.
 */
public class Vip {

    private String id;
    private String cloudId;
    private String vipCode;
    private String vipName;
    private String vipCardType;
    private String vipCardName;
    private String defaultDiscount;
    private String sex;
    private String birthday;
    private String mobilePhone;
    private String address;
    private int score;
    private String remarks;
    private int isUsed;
    private int delFlag;
    private int updateStatus;
    private Date updateTime;
    private String workAddress;
    private String phone;
    private String memberValidityDate;
    private int isLongValidity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCloudId() {
        return cloudId;
    }

    public void setCloudId(String cloudId) {
        this.cloudId = cloudId;
    }

    public String getVipCode() {
        return vipCode;
    }

    public void setVipCode(String vipCode) {
        this.vipCode = vipCode;
    }

    public String getVipName() {
        return vipName;
    }

    public void setVipName(String vipName) {
        this.vipName = vipName;
    }

    public String getVipCardType() {
        return vipCardType;
    }

    public void setVipCardType(String vipCardType) {
        this.vipCardType = vipCardType;
    }

    public String getVipCardName() {
        return vipCardName;
    }

    public void setVipCardName(String vipCardName) {
        this.vipCardName = vipCardName;
    }

    public String getDefaultDiscount() {
        return defaultDiscount;
    }

    public void setDefaultDiscount(String defaultDiscount) {
        this.defaultDiscount = defaultDiscount;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(int isUsed) {
        this.isUsed = isUsed;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public int getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(int updateStatus) {
        this.updateStatus = updateStatus;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMemberValidityDate() {
        return memberValidityDate;
    }

    public void setMemberValidityDate(String memberValidityDate) {
        this.memberValidityDate = memberValidityDate;
    }

    public int getIsLongValidity() {
        return isLongValidity;
    }

    public void setIsLongValidity(int isLongValidity) {
        this.isLongValidity = isLongValidity;
    }

    @Override
    public String toString() {
        return "Vip{" +
                "id='" + id + '\'' +
                ", cloudId='" + cloudId + '\'' +
                ", vipCode='" + vipCode + '\'' +
                ", vipName='" + vipName + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", address='" + address + '\'' +
                ", score=" + score +
                ", remarks='" + remarks + '\'' +
                ", isUsed=" + isUsed +
                ", delFlag=" + delFlag +
                ", updateStatus=" + updateStatus +
                ", updateTime=" + updateTime +
                ", workAddress='" + workAddress + '\'' +
                ", phone='" + phone + '\'' +
                ", memberValidityDate=" + memberValidityDate +
                ", isLongValidity=" + isLongValidity +
                '}';
    }
}
