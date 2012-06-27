package org.xpup.hafmis.sysloan.accounthandle.carryforward.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class CarryforwardTbShowAF extends ActionForm {

  /**
   * 
   */
  private static final long serialVersionUID = -5346910245042750833L;

  private String loanBankId = ""; // 查看对应的银行id

  private String contractId = ""; // 年结维护查询的合同编号

  private String borrowerName = ""; // 借款人姓名

  private String loanKouAcc = "";// 银行收款账号(贷款账号)

  private String carryforwardYear = ""; // 结息年

  private List list = new ArrayList(); // 列表
  
  public void reset(ActionMapping mapping, ServletRequest request) {
    loanBankId="";
    contractId="";
    borrowerName="";
    loanKouAcc="";
    carryforwardYear="";
    list = new ArrayList();
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getCarryforwardYear() {
    return carryforwardYear;
  }

  public void setCarryforwardYear(String carryforwardYear) {
    this.carryforwardYear = carryforwardYear;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getLoanBankId() {
    return loanBankId;
  }

  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }
}
