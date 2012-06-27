package org.xpup.hafmis.sysloan.loancallback.advancepayloan.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class AdvancepayloanTbAF extends ActionForm{
  
  private String contractId = "";//合同编号
  private String borrowerName = "";//借款人姓名
  private String cardNum = "";//证件号码
  private String status = "";//状态
  
  private List list = new ArrayList();
  
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
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  
}
