package com.evetime.cms.entity;

import java.io.Serializable;

/**
 * Created by Rains
 * on 2016-04-29.
 */
public class Branch implements Serializable{

    private String branchId;
    private String branchName;

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
