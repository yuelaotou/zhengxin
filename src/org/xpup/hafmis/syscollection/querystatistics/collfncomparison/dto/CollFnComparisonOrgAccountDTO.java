package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.dto;

import java.math.BigDecimal;

public class CollFnComparisonOrgAccountDTO {
  /**
   * 归集凭证号
   */
  private String doc_num = "";

  /**
   * 财务凭证号
   */
  private String credence_num = "";

  /**
   * 结算号
   */
  private String note_num = "";

  /**
   * 单位id
   */
  private String org_id = "";

  /**
   * 单位名称
   */
  private String name = "";


  /**
   * 业务类型
   */
  private String biz_type = "";

  
  /**
   * 业务类型
   */
  private String caiw_type = "";
  /**
   * 归集结算日期
   */
  private String collsett_date = "";


  /*
   * 归集业务状态
   */
  private String biz_status = "";

  /**
   * 财务结算日期
   */
  private String fnett_date = "";

  /**
   * 财务状态
   */
  private String credence_st = "";

  /**
   * 借方余额
   */
  private BigDecimal debit = new BigDecimal("0.00");

  /**
   * 贷方余额
   */

  private BigDecimal credit = new BigDecimal("0.00");
  
  /**
   * 余额
   */
  private BigDecimal moneySum = new BigDecimal("0.00");
  /**
   * 方向
   */
  private String aspect = "";
  /**
   * 销户利息
   */
  private BigDecimal distorybalance = new BigDecimal("0.00");
  
  private String type="";
  
  private String temp_collSt="";

  public String getBiz_status() {
    return biz_status;
  }

  public void setBiz_status(String biz_status) {
    this.biz_status = biz_status;
  }

  public String getBiz_type() {
    return biz_type;
  }

  public void setBiz_type(String biz_type) {
    this.biz_type = biz_type;
  }

  public String getCollsett_date() {
    return collsett_date;
  }

  public void setCollsett_date(String collsett_date) {
    this.collsett_date = collsett_date;
  }

  public String getCredence_num() {
    return credence_num;
  }

  public void setCredence_num(String credence_num) {
    this.credence_num = credence_num;
  }

  public String getCredence_st() {
    return credence_st;
  }

  public void setCredence_st(String credence_st) {
    this.credence_st = credence_st;
  }

  public BigDecimal getCredit() {
    return credit;
  }

  public void setCredit(BigDecimal credit) {
    this.credit = credit;
  }

  public BigDecimal getDebit() {
    return debit;
  }

  public void setDebit(BigDecimal debit) {
    this.debit = debit;
  }

  public String getDoc_num() {
    return doc_num;
  }

  public void setDoc_num(String doc_num) {
    this.doc_num = doc_num;
  }

  public String getFnett_date() {
    return fnett_date;
  }

  public void setFnett_date(String fnett_date) {
    this.fnett_date = fnett_date;
  }

  public BigDecimal getMoneySum() {
    return moneySum;
  }

  public void setMoneySum(BigDecimal moneySum) {
    this.moneySum = moneySum;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNote_num() {
    return note_num;
  }

  public void setNote_num(String note_num) {
    this.note_num = note_num;
  }

  public String getOrg_id() {
    return org_id;
  }

  public void setOrg_id(String org_id) {
    this.org_id = org_id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTemp_collSt() {
    return temp_collSt;
  }

  public void setTemp_collSt(String temp_collSt) {
    this.temp_collSt = temp_collSt;
  }

  public String getAspect() {
    return aspect;
  }

  public void setAspect(String aspect) {
    this.aspect = aspect;
  }

  public BigDecimal getDistorybalance() {
    return distorybalance;
  }

  public void setDistorybalance(BigDecimal distorybalance) {
    this.distorybalance = distorybalance;
  }

  public String getCaiw_type() {
    return caiw_type;
  }

  public void setCaiw_type(String caiw_type) {
    this.caiw_type = caiw_type;
  }

  
  
  
}
