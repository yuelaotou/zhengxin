package org.xpup.hafmis.syscollection.common.biz.loankoupop.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class LoanKouPopAF extends ActionForm {
  private String loanBankID = "";// 银行

  private String batchNum = "";// 批次号

  private List list;

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getBatchNum() {
    return batchNum;
  }

  public void setBatchNum(String batchNum) {
    this.batchNum = batchNum;
  }

  public String getLoanBankID() {
    return loanBankID;
  }

  public void setLoanBankID(String loanBankID) {
    this.loanBankID = loanBankID;
  }
}
