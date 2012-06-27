package org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * @author 王野 2007-10-05
 */
public class BailClearInterestTaAF extends ActionForm {

  private static final long serialVersionUID = 1235186860227030350L;

  private List list = null;

  private String id = null;

  private String loanBankName = null; // 放款银行

  private String paramExplainInterestD = null; // 定期利率

  private String paramExplainInterestL = null; // 活期利率

  private String loanKouAcc = null; // 贷款帐号

  private String contractId = null; // 合同ID

  private String borrowerName = null; // 借款人姓名

  private String cardNum = null; // 证件号码

  private BigDecimal bailBalance = new BigDecimal(0.00);// 结息前保证金

  private List loanBankNameList = new ArrayList();

  private BigDecimal bailBalanceTotle = new BigDecimal(0.00);// 结息前保证金-总额

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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public String getParamExplainInterestD() {
    return paramExplainInterestD;
  }

  public void setParamExplainInterestD(String paramExplainInterestD) {
    this.paramExplainInterestD = paramExplainInterestD;
  }

  public String getParamExplainInterestL() {
    return paramExplainInterestL;
  }

  public void setParamExplainInterestL(String paramExplainInterestL) {
    this.paramExplainInterestL = paramExplainInterestL;
  }

}