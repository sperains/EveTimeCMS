package com.evetime.app.dto;

import com.evetime.app.entity.Branch;
import com.evetime.app.entity.Brand;

import java.util.List;
import java.util.Map;

/**
 * Created by Rains
 * on 2016-07-04.
 */
public class BrandSaleInfo {

    private Brand brand;

    private List<Branch> branchList ;

    private List<Map<String , Object>> weekSaleList ;

    public BrandSaleInfo() {
    }

    public BrandSaleInfo(Brand brand, List<Branch> branchList, List<Map<String, Object>> weekSaleList) {
        this.brand = brand;
        this.branchList = branchList;
        this.weekSaleList = weekSaleList;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Branch> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<Branch> branchList) {
        this.branchList = branchList;
    }

    public List<Map<String, Object>> getWeekSaleList() {
        return weekSaleList;
    }

    public void setWeekSaleList(List<Map<String, Object>> weekSaleList) {
        this.weekSaleList = weekSaleList;
    }

    @Override
    public String toString() {
        return "BrandSaleInfo{" +
                "brand=" + brand +
                ", branchList=" + branchList +
                ", weekSaleList=" + weekSaleList +
                '}';
    }
}
