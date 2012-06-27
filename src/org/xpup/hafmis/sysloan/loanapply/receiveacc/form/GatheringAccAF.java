package org.xpup.hafmis.sysloan.loanapply.receiveacc.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class GatheringAccAF extends ActionForm {
  // 合同编号
  private String contractId = "";

  // 借款人姓名
  private String borrowerName = "";

  // 证件号码
  private String cardNum = "";

  // 列表的list
  List list = new ArrayList();

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
}
