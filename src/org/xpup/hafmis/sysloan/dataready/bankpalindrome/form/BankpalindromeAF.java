package org.xpup.hafmis.sysloan.dataready.bankpalindrome.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
/**
 * 
 * @author yuqf
 *2007-12-20
 */
public class BankpalindromeAF extends ActionForm{
  private String bankId = "";
  private String rowNum = "";
  private List loanBankNameList = new ArrayList();
  
  public String getBankId() {
    return bankId;
  }
  public void setBankId(String bankId) {
    this.bankId = bankId;
  }

  public List getLoanBankNameList() {
    return loanBankNameList;
  }
  public void setLoanBankNameList(List loanBankNameList) {
    this.loanBankNameList = loanBankNameList;
  }
  public String getRowNum() {
    return rowNum;
  }
  public void setRowNum(String rowNum) {
    this.rowNum = rowNum;
  }
  
}
