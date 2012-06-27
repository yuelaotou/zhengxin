package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto;

import java.math.BigDecimal;

/**
 * 封装了错帐调整弹出框中列表信息的DTO
 * 
 * @author 付云峰
 */
public class AdjustAccountPopListDTO {

  /**
   * PL202id
   */
  private Integer flowHeadId = new Integer(0);

  /**
   * 贷款帐号
   */
  private String loanKouAcc = "";

  /**
   * 凭证编号
   */
  private String docNum = "";

  /**
   * 合同编号
   */
  private String contractId = "";

  /**
   * 借款人姓名
   */
  private String borrowerName = "";

  /**
   * 证件号码
   */
  private String cardNum = "";

  /**
   * 发放金额
   */
  private BigDecimal occurMoney = new BigDecimal(0.00);

  /**
   * 本次还款额
   */
  private BigDecimal loanBackPay = new BigDecimal(0.00);

  /**
   * 挂账发生额
   */
  private BigDecimal overPay = new BigDecimal(0.00);

  /**
   * 实收金额
   */
  private BigDecimal factPay = new BigDecimal(0.00);

  /**
   * 业务类型
   */
  private String bizType = "";

  /**
   * 放款银行id
   */
  private String loanbankid = "";
  
  /**
   * 业务日期
   */
  private String bizdate = "";

  public String getBizdate() {
    return bizdate;
  }

  public void setBizdate(String bizdate) {
    this.bizdate = bizdate;
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

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public BigDecimal getFactPay() {
    return factPay;
  }

  public void setFactPay(BigDecimal factPay) {
    this.factPay = factPay;
  }

  public BigDecimal getLoanBackPay() {
    return loanBackPay;
  }

  public void setLoanBackPay(BigDecimal loanBackPay) {
    this.loanBackPay = loanBackPay;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public BigDecimal getOccurMoney() {
    return occurMoney;
  }

  public void setOccurMoney(BigDecimal occurMoney) {
    this.occurMoney = occurMoney;
  }

  public BigDecimal getOverPay() {
    return overPay;
  }

  public void setOverPay(BigDecimal overPay) {
    this.overPay = overPay;
  }

  public Integer getFlowHeadId() {
    return flowHeadId;
  }

  public void setFlowHeadId(Integer flowHeadId) {
    this.flowHeadId = flowHeadId;
  }

  public String getLoanbankid() {
    return loanbankid;
  }

  public void setLoanbankid(String loanbankid) {
    this.loanbankid = loanbankid;
  }

}
