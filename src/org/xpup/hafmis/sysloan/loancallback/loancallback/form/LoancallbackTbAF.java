package org.xpup.hafmis.sysloan.loancallback.loancallback.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.CriterionsAF;

public class LoancallbackTbAF extends CriterionsAF {
  
  private static final long serialVersionUID = 157830469042818336L;

  // 合同编号
  private String contractId = "";
  
  // 姓名
  private String borrowerName = "";

  // 证件号码
  private String cardNum = "";
  
  //放款银行
  private String loanBankId = "";
  
  //业务状态
  private String status = "";
  
  //贷款账号
  private String loanKouAcc = "";
  
  //凭证编号
  private String docNum = "";
  
  //业务类型
  private String type = "";
  
  //回收日期
  private String dateStart = "";
  private String dateEnd = "";
  private String yesOrNo = "";//是否为公积金还贷
  
  private Map typeMap = new HashMap();
  
  private Map statusMap = new HashMap();
  
  private Map yesornoMap = new HashMap();
  
  //还至年月列表
  private List monthYearList = new ArrayList();
  
  public List getMonthYearList() {
    return monthYearList;
  }

  public void setMonthYearList(List monthYearList) {
    this.monthYearList = monthYearList;
  }

  public Map getStatusMap() {
    return statusMap;
  }

  public void setStatusMap(Map statusMap) {
    this.statusMap = statusMap;
  }

  public Map getTypeMap() {
    return typeMap;
  }

  public void setTypeMap(Map typeMap) {
    this.typeMap = typeMap;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public String getLoanBankId() {
    return loanBankId;
  }

  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
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


  public void reset(ActionMapping mapping, ServletRequest request) {
    borrowerName = "";
    cardNum = "";
    contractId = "";
    loanBankId = "";
    status ="";
    type = "";
    docNum = "";
    loanKouAcc = "";
  }

  public String getDateEnd() {
    return dateEnd;
  }

  public void setDateEnd(String dateEnd) {
    this.dateEnd = dateEnd;
  }

  public String getDateStart() {
    return dateStart;
  }

  public void setDateStart(String dateStart) {
    this.dateStart = dateStart;
  }

  public String getYesOrNo() {
    return yesOrNo;
  }

  public void setYesOrNo(String yesOrNo) {
    this.yesOrNo = yesOrNo;
  }

  public Map getYesornoMap() {
    return yesornoMap;
  }

  public void setYesornoMap(Map yesornoMap) {
    this.yesornoMap = yesornoMap;
  }

}