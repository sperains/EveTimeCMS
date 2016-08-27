package com.evetime.cms.entity;

import java.io.Serializable;

/**
 * Created by Rainson 2016-04-25.
 *
 */
public class WithdrawCash implements Serializable {

    private String orderNo ;     //提现订单号
    private String realName ;   //真实姓名
    private String cardNumber; //卡号
    private Double money  ;       //提现金额
    private String cashTime ;    //提款时间
    private String cloudId ;       //云端账号id
    private String bankName ;  //所在银行
    private Integer cashStatus ;   //提款状态
    private String applyDate ;    //申请时间
    private String opDate ;        //处理时间
    private String remark ;        //备注

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getCashTime() {
        return cashTime;
    }

    public void setCashTime(String cashTime) {
        this.cashTime = cashTime;
    }

    public String getCloudId() {
        return cloudId;
    }

    public void setCloudId(String cloudId) {
        this.cloudId = cloudId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Integer getCashStatus() {
        return cashStatus;
    }

    public void setCashStatus(Integer cashStatus) {
        this.cashStatus = cashStatus;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getOpDate() {
        return opDate;
    }

    public void setOpDate(String opDate) {
        this.opDate = opDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
