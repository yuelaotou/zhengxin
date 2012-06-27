package org.xpup.hafmis.syscollection.querystatistics.cumulativeinfo.dto;

import java.math.BigDecimal;

import org.apache.struts.action.ActionForm;

public class Fundbankmonthofyeardto extends ActionForm{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private BigDecimal pay_fund = new BigDecimal(0.00);//归集额;
  
  private BigDecimal org_neworg = new BigDecimal(0.00);//户数--新发展单位;
  private BigDecimal person_neworg = new BigDecimal(0.00);//人数--新发展单位;
  private BigDecimal pay_neworg = new BigDecimal(0.00);//缴额--新发展单位;
  
  private BigDecimal org_rate = new BigDecimal(0.00);//户数--比例;
  private BigDecimal pay_rate = new BigDecimal(0.00);//缴额--比例;
  
  private BigDecimal org_salary = new BigDecimal(0.00);//户数--工资基数;
  private BigDecimal pay_salary = new BigDecimal(0.00);//缴额--工资基数;
  
  private BigDecimal after_pay = new BigDecimal(0.00);//补缴;
  
  private BigDecimal org_over = new BigDecimal(0.00);//户数--本月欠缴;
  private BigDecimal pay_over = new BigDecimal(0.00);//缴额--本月欠缴;
  
  private BigDecimal org_overs = new BigDecimal(0.00);//户数--欠1月以上;
  private BigDecimal pay_overs = new BigDecimal(0.00);//缴额--欠1月以上;
  
  public BigDecimal getAfter_pay() {
    return after_pay;
  }
  public void setAfter_pay(BigDecimal after_pay) {
    this.after_pay = after_pay;
  }
  public BigDecimal getOrg_neworg() {
    return org_neworg;
  }
  public void setOrg_neworg(BigDecimal org_neworg) {
    this.org_neworg = org_neworg;
  }
  public BigDecimal getOrg_over() {
    return org_over;
  }
  public void setOrg_over(BigDecimal org_over) {
    this.org_over = org_over;
  }
  public BigDecimal getOrg_overs() {
    return org_overs;
  }
  public void setOrg_overs(BigDecimal org_overs) {
    this.org_overs = org_overs;
  }
  public BigDecimal getOrg_rate() {
    return org_rate;
  }
  public void setOrg_rate(BigDecimal org_rate) {
    this.org_rate = org_rate;
  }
  public BigDecimal getOrg_salary() {
    return org_salary;
  }
  public void setOrg_salary(BigDecimal org_salary) {
    this.org_salary = org_salary;
  }
  public BigDecimal getPay_fund() {
    return pay_fund;
  }
  public void setPay_fund(BigDecimal pay_fund) {
    this.pay_fund = pay_fund;
  }
  public BigDecimal getPay_neworg() {
    return pay_neworg;
  }
  public void setPay_neworg(BigDecimal pay_neworg) {
    this.pay_neworg = pay_neworg;
  }
  public BigDecimal getPay_over() {
    return pay_over;
  }
  public void setPay_over(BigDecimal pay_over) {
    this.pay_over = pay_over;
  }
  public BigDecimal getPay_overs() {
    return pay_overs;
  }
  public void setPay_overs(BigDecimal pay_overs) {
    this.pay_overs = pay_overs;
  }
  public BigDecimal getPay_rate() {
    return pay_rate;
  }
  public void setPay_rate(BigDecimal pay_rate) {
    this.pay_rate = pay_rate;
  }
  public BigDecimal getPay_salary() {
    return pay_salary;
  }
  public void setPay_salary(BigDecimal pay_salary) {
    this.pay_salary = pay_salary;
  }
  public BigDecimal getPerson_neworg() {
    return person_neworg;
  }
  public void setPerson_neworg(BigDecimal person_neworg) {
    this.person_neworg = person_neworg;
  }

}
