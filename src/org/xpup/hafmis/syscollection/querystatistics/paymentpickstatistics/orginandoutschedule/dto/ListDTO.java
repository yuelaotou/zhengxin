package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orginandoutschedule.dto;

import java.math.BigDecimal;

public class ListDTO {
  
  private Integer orgid;//单位编号
 
  private String orgname; //单位名称
  
  private BigDecimal jzbalance=new BigDecimal(0.00);//结转余额
  
  private BigDecimal jzgzbalance=new BigDecimal(0.00);//结转挂帐余额
  
  private BigDecimal jzzmbalance=new BigDecimal(0.00);//结转账面余额
  
  private BigDecimal gzpay=new BigDecimal(0.00);//挂帐金额
  
  private BigDecimal payment=new BigDecimal(0.00);//汇缴
  
  private BigDecimal addpay=new BigDecimal(0.00);//补缴
  
  private BigDecimal adjustaccount=new BigDecimal(0.00);//调帐
  
  private BigDecimal tanin=new BigDecimal(0.00);//转入
  
  private BigDecimal interest=new BigDecimal(0.00);//利息
  
  private BigDecimal pick=new BigDecimal(0.00);//提取
  
  private BigDecimal tranout=new BigDecimal(0.00);//转出
  
  private BigDecimal currentbalance=new BigDecimal(0.00);//当前余额
  
  private BigDecimal gzbalance=new BigDecimal(0.00);//挂帐余额
  
  private BigDecimal zmbalance=new BigDecimal(0.00);//账面余额
  public BigDecimal getAddpay() {
    return addpay;
  }
  public void setAddpay(BigDecimal addpay) {
    this.addpay = addpay;
  }
  public BigDecimal getAdjustaccount() {
    return adjustaccount;
  }
  public void setAdjustaccount(BigDecimal adjustaccount) {
    this.adjustaccount = adjustaccount;
  }
  public BigDecimal getCurrentbalance() {
    return currentbalance;
  }
  public void setCurrentbalance(BigDecimal currentbalance) {
    this.currentbalance = currentbalance;
  }
  public BigDecimal getGzbalance() {
    return gzbalance;
  }
  public void setGzbalance(BigDecimal gzbalance) {
    this.gzbalance = gzbalance;
  }
  public BigDecimal getGzpay() {
    return gzpay;
  }
  public void setGzpay(BigDecimal gzpay) {
    this.gzpay = gzpay;
  }
  public BigDecimal getInterest() {
    return interest;
  }
  public void setInterest(BigDecimal interest) {
    this.interest = interest;
  }
  public BigDecimal getJzbalance() {
    return jzbalance;
  }
  public void setJzbalance(BigDecimal jzbalance) {
    this.jzbalance = jzbalance;
  }
  public BigDecimal getJzgzbalance() {
    return jzgzbalance;
  }
  public void setJzgzbalance(BigDecimal jzgzbalance) {
    this.jzgzbalance = jzgzbalance;
  }
  public BigDecimal getJzzmbalance() {
    return jzzmbalance;
  }
  public void setJzzmbalance(BigDecimal jzzmbalance) {
    this.jzzmbalance = jzzmbalance;
  }
  public Integer getOrgid() {
    return orgid;
  }
  public void setOrgid(Integer orgid) {
    this.orgid = orgid;
  }
  public String getOrgname() {
    return orgname;
  }
  public void setOrgname(String orgname) {
    this.orgname = orgname;
  }
  public BigDecimal getPayment() {
    return payment;
  }
  public void setPayment(BigDecimal payment) {
    this.payment = payment;
  }
  public BigDecimal getPick() {
    return pick;
  }
  public void setPick(BigDecimal pick) {
    this.pick = pick;
  }
  public BigDecimal getTanin() {
    return tanin;
  }
  public void setTanin(BigDecimal tanin) {
    this.tanin = tanin;
  }
  public BigDecimal getTranout() {
    return tranout;
  }
  public void setTranout(BigDecimal tranout) {
    this.tranout = tranout;
  }
  public BigDecimal getZmbalance() {
    return zmbalance;
  }
  public void setZmbalance(BigDecimal zmbalance) {
    this.zmbalance = zmbalance;
  }
}
