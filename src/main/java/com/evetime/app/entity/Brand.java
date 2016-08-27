package com.evetime.app.entity;

/**
 * Created by rains
 * on 2016-07-04.
 */
public class Brand {

    private String systemId ;                       //品牌Id

    private String name;                            //品牌名称

    private Double todaySale ;                  //当日营业额

    private String imgUrl ;                         //logo图片地址

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

    public Double getTodaySale() {
        return todaySale;
    }

    public void setTodaySale(Double todaySale) {
        this.todaySale = todaySale;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "systemId='" + systemId + '\'' +
                ", name='" + name + '\'' +
                ", todaySale=" + todaySale +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
