package com.evetime.cms.entity;

import java.io.Serializable;

/**
 * Created by Rains on 2016-04-21.
 *
 */
public class PayOrder implements Serializable{

    private String id;                                      //支付订单Id,
    private String orderNo;                            //订单号
    private String payDate;                            //支付时间
    private String payTypeName;                   //支付类型
    private String payMoney;                         //消费金额


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }
}
