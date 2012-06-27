package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto;

import java.math.BigDecimal;
/**
 * 封装了办理错帐调整页面信息的DTO
 * @author 付云峰
 *
 */
public class AdjustAccountTaInfoDTO {
  /**
   * PL202头表id
   */
  private String flowHeadId = "";

  /**
   * 贷款帐号
   */
  private String loanKouAcc = "";

  /**
   * 凭证编号
   */
  private String docNum = "";

  /**
   * 业务类型
   */
  private String bizType = "";
  /**
   * 业务类型String型
   */
  private String strBizType = "";

  /**
   * 发放金额
   */
  private BigDecimal putOutMoney = new BigDecimal(0.00);

  /**
   * 回收金额
   */
  private BigDecimal callbackMoney = new BigDecimal(0.00);

  /**
   * 回收利息
   */
  private BigDecimal callbackInterest = new BigDecimal(0.00);

  /**
   * 罚息利息
   */
  private BigDecimal punishInterest = new BigDecimal(0.00);

  /**
   * 借款人姓名
   */
  private String borrowerName = "";

  /**
   * 制单人
   */
  private String makePerson = "";

  /**
   * 自动挂账
   */
  private String autoOverPay = "";
  
  /**
   * 还款年月
   */
  private String yearMonth = "";

  public String getYearMonth() {
    return yearMonth;
  }

  public void setYearMonth(String yearMonth) {
    this.yearMonth = yearMonth;
  }

  public String getAutoOverPay() {
    return autoOverPay;
  }

  public void setAutoOverPay(String autoOverPay) {
    this.autoOverPay = autoOverPay;
  }

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public BigDecimal getCallbackInterest() {
    return callbackInterest;
  }

  public void setCallbackInterest(BigDecimal callbackInterest) {
    this.callbackInterest = callbackInterest;
  }

  public BigDecimal getCallbackMoney() {
    return callbackMoney;
  }

  public void setCallbackMoney(BigDecimal callbackMoney) {
    this.callbackMoney = callbackMoney;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public String getFlowHeadId() {
    return flowHeadId;
  }

  public void setFlowHeadId(String flowHeadId) {
    this.flowHeadId = flowHeadId;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public String getMakePerson() {
    return makePerson;
  }

  public void setMakePerson(String makePerson) {
    this.makePerson = makePerson;
  }

  public BigDecimal getPunishInterest() {
    return punishInterest;
  }

  public void setPunishInterest(BigDecimal punishInterest) {
    this.punishInterest = punishInterest;
  }

  public BigDecimal getPutOutMoney() {
    return putOutMoney;
  }

  public void setPutOutMoney(BigDecimal putOutMoney) {
    this.putOutMoney = putOutMoney;
  }

  public String getStrBizType() {
    return strBizType;
  }

  public void setStrBizType(String strBizType) {
    this.strBizType = strBizType;
  }
}
