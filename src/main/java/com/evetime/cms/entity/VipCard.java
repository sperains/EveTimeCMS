package com.evetime.cms.entity;

import java.util.Date;

/**
 * Created by Rains
 * on 2016-06-21.
 */
public class VipCard {

    private String id;
    private String cloudId;
    private String vipCardCode;
    private String vipCardName;
    private Integer baseScore;
    private Integer levelScore;
    private Double defaultDiscount;
    private Integer isSynchronization;
    private Integer delFlag;
    private Integer updateStatus;
    private Date updateTime;

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

    public String getVipCardCode() {
        return vipCardCode;
    }

    public void setVipCardCode(String vipCardCode) {
        this.vipCardCode = vipCardCode;
    }

    public String getVipCardName() {
        return vipCardName;
    }

    public void setVipCardName(String vipCardName) {
        this.vipCardName = vipCardName;
    }

    public Integer getBaseScore() {
        return baseScore;
    }

    public void setBaseScore(Integer baseScore) {
        this.baseScore = baseScore;
    }

    public Integer getLevelScore() {
        return levelScore;
    }

    public void setLevelScore(Integer levelScore) {
        this.levelScore = levelScore;
    }

    public Double getDefaultDiscount() {
        return defaultDiscount;
    }

    public void setDefaultDiscount(Double defaultDiscount) {
        this.defaultDiscount = defaultDiscount;
    }

    public Integer getIsSynchronization() {
        return isSynchronization;
    }

    public void setIsSynchronization(Integer isSynchronization) {
        this.isSynchronization = isSynchronization;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(Integer updateStatus) {
        this.updateStatus = updateStatus;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "VipCard{" +
                "id='" + id + '\'' +
                ", cloudId='" + cloudId + '\'' +
                ", vipCardCode='" + vipCardCode + '\'' +
                ", vipCardName='" + vipCardName + '\'' +
                ", baseScore=" + baseScore +
                ", levelScore=" + levelScore +
                ", defaultDiscount=" + defaultDiscount +
                ", isSynchronization=" + isSynchronization +
                ", delFlag=" + delFlag +
                ", updateStatus=" + updateStatus +
                ", updateTime=" + updateTime +
                '}';
    }
}
