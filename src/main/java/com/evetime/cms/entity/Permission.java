package com.evetime.cms.entity;

/**
 * Created by Rains
 * on 2016-05-18.
 */
public class Permission {

    private String userId;
    private String brandId;
    private String cloudId;
    private String restaurantId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getCloudId() {
        return cloudId;
    }

    public void setCloudId(String cloudId) {
        this.cloudId = cloudId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "userId='" + userId + '\'' +
                ", brandId='" + brandId + '\'' +
                ", branchId='" + cloudId + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                '}';
    }
}
