package org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanxieyi.form;

import java.util.ArrayList;

import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * @author 王野 2007-10-15
 */
public class LoanXieYiAF extends ActionForm {

  private static final long serialVersionUID = 2531807195056023196L;

  List list = null;// 显示列表

  List printList = null;// 打印列表

  private String office = "";// 制单人

  private String officename = "";
  
  private String borrowerName = "";// 借款人姓名
  
  private String contractId = "";// 合同编号

  private List loanBankNameList = new ArrayList();// 放款银行

  private String loanBankName = null;// 放款银行

  private String beginBizDate = null;// 起始办理日期

  private String endBizDate = null;// 终止办理日期

  private String beginDelDate = null;// 起始撤消日期

  private String endDelDate = null;// 终止撤消日期

  private String count = "0";// 人数

  private String isDelete = "";// 协议是否撤消

  public String getBeginBizDate() {
    return beginBizDate;
  }

  public void setBeginBizDate(String beginBizDate) {
    this.beginBizDate = beginBizDate;
  }

  public String getBeginDelDate() {
    return beginDelDate;
  }

  public void setBeginDelDate(String beginDelDate) {
    this.beginDelDate = beginDelDate;
  }

  public String getEndBizDate() {
    return endBizDate;
  }

  public void setEndBizDate(String endBizDate) {
    this.endBizDate = endBizDate;
  }

  public String getEndDelDate() {
    return endDelDate;
  }

  public void setEndDelDate(String endDelDate) {
    this.endDelDate = endDelDate;
  }

  public String getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(String isDelete) {
    this.isDelete = isDelete;
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

  public List getPrintList() {
    return printList;
  }

  public void setPrintList(List printList) {
    this.printList = printList;
  }

  public String getOfficename() {
    return officename;
  }

  public void setOfficename(String officename) {
    this.officename = officename;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getCount() {
    return count;
  }

  public void setCount(String count) {
    this.count = count;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

}
