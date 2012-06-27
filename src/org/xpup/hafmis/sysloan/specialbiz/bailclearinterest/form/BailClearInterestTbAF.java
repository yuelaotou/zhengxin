package org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * @author 王野 2007-10-05
 */
public class BailClearInterestTbAF extends ActionForm {

  private static final long serialVersionUID = -7589681596773601590L;

  private List list = null;
  
  private List printList = null;// 打印list

  private String id = null;

  private String bizYear = null; // 结息年度
  
  private String loanBankName = null; // 放款银行

  private String loanKouAcc = null; // PL203 LOAN_KOU_ACC 贷款帐号

  private String borrowerName = null; // 借款人姓名

  private BigDecimal bailBalance = new BigDecimal(0.00);// PL111 BAIL_BALANCE 结息前保证金
  
  private BigDecimal occurMoney = new BigDecimal(0.00);// PL203 OCCUR_MONEY 结息利息  

  private BigDecimal lastBalance = new BigDecimal(0.00);// 结息后保证金 = 结息前保证金 + 结息利息

  private List loanBankNameList = new ArrayList();

  private BigDecimal bailBalanceTotle = new BigDecimal(0.00);// 结息前保证金-总额
  
  private BigDecimal occurMoneyTotle = new BigDecimal(0.00);// 结息利息-总额
  
  private BigDecimal lastBalanceTotle = new BigDecimal(0.00);// 结息后保证金-总额

  public BigDecimal getBailBalance() {
    return bailBalance;
  }

  public void setBailBalance(BigDecimal bailBalance) {
    this.bailBalance = bailBalance;
  }

  public BigDecimal getBailBalanceTotle() {
    return bailBalanceTotle;
  }

  public void setBailBalanceTotle(BigDecimal bailBalanceTotle) {
    this.bailBalanceTotle = bailBalanceTotle;
  }

  public String getBizYear() {
    return bizYear;
  }

  public void setBizYear(String bizYear) {
    this.bizYear = bizYear;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public BigDecimal getLastBalance() {
    return lastBalance;
  }

  public void setLastBalance(BigDecimal lastBalance) {
    this.lastBalance = lastBalance;
  }

  public BigDecimal getLastBalanceTotle() {
    return lastBalanceTotle;
  }

  public void setLastBalanceTotle(BigDecimal lastBalanceTotle) {
    this.lastBalanceTotle = lastBalanceTotle;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getLoanBankName() {
    return loanBankName;
  }

  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }

  public List getLoanBankNameList() {
    return loanBankNameList;
  }

  public void setLoanBankNameList(List loanBankNameList) {
    this.loanBankNameList = loanBankNameList;
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

  public BigDecimal getOccurMoneyTotle() {
    return occurMoneyTotle;
  }

  public void setOccurMoneyTotle(BigDecimal occurMoneyTotle) {
    this.occurMoneyTotle = occurMoneyTotle;
  }

  public List getPrintList() {
    return printList;
  }

  public void setPrintList(List printList) {
    this.printList = printList;
  }

}