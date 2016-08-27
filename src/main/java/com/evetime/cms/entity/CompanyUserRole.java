package com.evetime.cms.entity;

/**
 * Created by Rains
 * on 2016-07-18.
 */
public class CompanyUserRole {

    private String userId ;
    private String restaurantId;
    private String brandId;
    private String cloudId ;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanyUserRole)) return false;

        CompanyUserRole that = (CompanyUserRole) o;

        if (!getUserId().equals(that.getUserId())) return false;
        return getCloudId().equals(that.getCloudId());

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (cloudId != null ? cloudId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CompanyUserRole{" +
                "userId='" + userId + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                ", brandId='" + brandId + '\'' +
                ", cloudId='" + cloudId + '\'' +
                '}';
    }
}
