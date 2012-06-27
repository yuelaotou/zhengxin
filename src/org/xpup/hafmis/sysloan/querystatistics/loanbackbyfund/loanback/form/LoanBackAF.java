package org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanback.form;

import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * @author 王野 2007-10-15
 */
public class LoanBackAF extends ActionForm {

  private static final long serialVersionUID = 2531807195056023196L;

  List list = null;// 显示列表

  List printList = null;// 打印列表

  List bankList_yg = null;

  private String office = "";// 制单人

  private String officename = "";

  private List loanBankNameList = new ArrayList();// 放款银行

  private String loanBankName = null;// 放款银行

  private String contractId = null;// 起始办理日期

  private String borrowerName = null;// 终止办理日期

  private String borrowerCardNum = null;// 起始撤消日期

  private String docNum = null;

  private String noteNum = null;

  private String beginBizDate = "";// 协议是否撤消

  private String endBizDate = "";

  private String orgId = "";

  private String orgName = "";

  private String empId = "";

  private BigDecimal data_1 = new BigDecimal("0.00");

  private BigDecimal data_2 = new BigDecimal("0.00");

  private BigDecimal data_3 = new BigDecimal("0.00");

  private BigDecimal data_4 = new BigDecimal("0.00");

  private String printDate = "";

  public String getBorrowerCardNum() {
    return borrowerCardNum;
  }

  public void setBorrowerCardNum(String borrowerCardNum) {
    this.borrowerCardNum = borrowerCardNum;
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

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
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

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getOfficename() {
    return officename;
  }

  public void setOfficename(String officename) {
    this.officename = officename;
  }

  public List getPrintList() {
    return printList;
  }

  public void setPrintList(List printList) {
    this.printList = printList;
  }

  public String getBeginBizDate() {
    return beginBizDate;
  }

  public void setBeginBizDate(String beginBizDate) {
    this.beginBizDate = beginBizDate;
  }

  public String getEndBizDate() {
    return endBizDate;
  }

  public void setEndBizDate(String endBizDate) {
    this.endBizDate = endBizDate;
  }

  public BigDecimal getData_1() {
    return data_1;
  }

  public void setData_1(BigDecimal data_1) {
    this.data_1 = data_1;
  }

  public BigDecimal getData_2() {
    return data_2;
  }

  public void setData_2(BigDecimal data_2) {
    this.data_2 = data_2;
  }

  public BigDecimal getData_3() {
    return data_3;
  }

  public void setData_3(BigDecimal data_3) {
    this.data_3 = data_3;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public List getBankList_yg() {
    return bankList_yg;
  }

  public void setBankList_yg(List bankList_yg) {
    this.bankList_yg = bankList_yg;
  }

  public BigDecimal getData_4() {
    return data_4;
  }

  public void setData_4(BigDecimal data_4) {
    this.data_4 = data_4;
  }

  public String getPrintDate() {
    return printDate;
  }

  public void setPrintDate(String printDate) {
    this.printDate = printDate;
  }

}
