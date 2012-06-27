package org.xpup.hafmis.sysloan.loancallback.destoryback.form;
import org.apache.struts.action.ActionForm;
public class DestoryBackTbWindowAF extends ActionForm {
  private String loanKouAcc = "";// 贷款账号

  private String contractId = "";// 合同编号

  private String borrowerName = "";// 借款人姓名

  private String cardKindName = ""; // 显示证件类型对应的名称

  private String cardNum = ""; // 证件号码

  private String loanModeName="";//还款方式
  

  
  private String backUnit= "";//收回单位
  
  private String backunitName= "";//收回单位名称
  
  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

 

  public String getCardKindName() {
    return cardKindName;
  }

  public void setCardKindName(String cardKindName) {
    this.cardKindName = cardKindName;
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

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

 
  public String getLoanModeName() {
    return loanModeName;
  }

  public void setLoanModeName(String loanModeName) {
    this.loanModeName = loanModeName;
  }

 

  public String getBackUnit() {
    return backUnit;
  }

  public void setBackUnit(String backUnit) {
    this.backUnit = backUnit;
  }

  public String getBackunitName() {
    return backunitName;
  }

  public void setBackunitName(String backunitName) {
    this.backunitName = backunitName;
  }
  
}
