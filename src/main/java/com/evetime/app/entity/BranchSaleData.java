package com.evetime.app.entity;

/**
 * Created by Rains
    销售数据
 * on 2016-07-04.
 */
public class BranchSaleData {
    private String branchId ;                       //分店Id
    private Integer orderCount ;                //客单数
    private Integer foodSellCount ;            //菜品销售数量
    private Integer averageCost ;               //人均消费
    private Double  tableTurnoverRate ;     //翻台率
    private String date;                              //日期
    private Double sale;                             //当日总营收

    public BranchSaleData() {
    }

    public BranchSaleData(Integer orderCount, Integer foodSellCount, Integer averageCost, Double tableTurnoverRate) {
        this.orderCount = orderCount;
        this.foodSellCount = foodSellCount;
        this.averageCost = averageCost;
        this.tableTurnoverRate = tableTurnoverRate;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getFoodSellCount() {
        return foodSellCount;
    }

    public void setFoodSellCount(Integer foodSellCount) {
        this.foodSellCount = foodSellCount;
    }

    public Integer getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(Integer averageCost) {
        this.averageCost = averageCost;
    }

    public Double getTableTurnoverRate() {
        return tableTurnoverRate;
    }

    public void setTableTurnoverRate(Double tableTurnoverRate) {
        this.tableTurnoverRate = tableTurnoverRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getSale() {
        return sale;
    }

    public void setSale(Double sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "SaleData{" +
                "branchId='" + branchId + '\'' +
                ", orderCount=" + orderCount +
                ", foodSellCount=" + foodSellCount +
                ", averageCost=" + averageCost +
                ", tableTurnoverRate=" + tableTurnoverRate +
                ", date='" + date + '\'' +
                ", sale=" + sale +
                '}';
    }
}
