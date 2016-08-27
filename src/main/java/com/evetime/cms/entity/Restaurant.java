package com.evetime.cms.entity;

import java.io.Serializable;

/**
 * Created by Rains on 2016-04-21.
 *
 */
public class Restaurant implements Serializable {

    private String cloudId;
    private String RestaurantName;
    private String BranchName;

    public String getCloudId() {
        return cloudId;
    }

    public void setCloudId(String cloudId) {
        this.cloudId = cloudId;
    }

    public String getRestaurantName() {
        return RestaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        RestaurantName = restaurantName;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String branchName) {
        BranchName = branchName;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "cloudId='" + cloudId + '\'' +
                ", RestaurantName='" + RestaurantName + '\'' +
                ", BranchName='" + BranchName + '\'' +
                '}';
    }
}
