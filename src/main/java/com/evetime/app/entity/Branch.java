package com.evetime.app.entity;

/**
 * Created by Rains
 * on 2016-07-04.
 */
public class Branch {

    private String systemId;                                //分店Id
    //private String imgUrl;                                  //分店logo地址
    private String name ;                                   //分店名称
    private Double sale;                           //分店当日营收

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSale() {
        return sale;
    }

    public void setSale(Double sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "systemId='" + systemId + '\'' +
                ", name='" + name + '\'' +
                ", sale=" + sale +
                '}';
    }
}
