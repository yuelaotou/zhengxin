package org.xpup.hafmis.sysloan.common.biz.loankouaccpop.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.CriterionsAF;

public class LoanKouAccpopAF extends CriterionsAF{
  private String loankouacc = "";//贷款账号
  private String contractId = ""; //合同ID
  private String borrowerName = "";  //BORROWER_NAME 借款人姓名
  private String cardNum = "";  //证件号码
  private String empId = ""; //职工ID
  
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
  public String getEmpId() {
    return empId;
  }
  public void setEmpId(String empId) {
    this.empId = empId;
  }
  
  public void reset(ActionMapping mapping, HttpServletRequest request) {
    super.reset(mapping, request);
    loankouacc = "";
    contractId = "";
    borrowerName = "";
    cardNum = "";
    empId = "";
  }
  public String getLoankouacc() {
    return loankouacc;
  }
  public void setLoankouacc(String loankouacc) {
    this.loankouacc = loankouacc;
  }
}
