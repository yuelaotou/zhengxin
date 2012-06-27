package org.xpup.hafmis.sysloan.loanapply.issuenotice.form;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class IssuenoticeTbAF extends ActionForm {
  private List list = null;

  private String contractId = "";// 合同编号

  private String borrowerName = "";// 借款人姓名

  private String cardNum = "";// 证件号码

  private String loanBankId = "";// 放款银行

  private BigDecimal loanMoneySum = new BigDecimal(0.00);// 借款金额

  private String isDelete = "";
  
  private String type="";
  
  private String contractSt="";
  
  private String isPrint="";
  
  private String ischecked="";

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

  public void reset() {
    this.contractId = "";
    this.borrowerName = "";
    this.cardNum = "";
    this.loanBankId = "";

  }

  public BigDecimal getLoanMoneySum() {
    return loanMoneySum;
  }

  public void setLoanMoneySum(BigDecimal loanMoneySum) {
    this.loanMoneySum = loanMoneySum;
  }

  public String getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(String isDelete) {
    this.isDelete = isDelete;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getContractSt() {
    return contractSt;
  }

  public void setContractSt(String contractSt) {
    this.contractSt = contractSt;
  }

  public String getIsPrint() {
    return isPrint;
  }

  public void setIsPrint(String isPrint) {
    this.isPrint = isPrint;
  }

  public String getIschecked() {
    return ischecked;
  }

  public void setIschecked(String ischecked) {
    this.ischecked = ischecked;
  }
}
