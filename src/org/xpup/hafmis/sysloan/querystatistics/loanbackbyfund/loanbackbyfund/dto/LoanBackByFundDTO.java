package org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanbackbyfund.dto;

/**
 * @author ั๎นโ 20090303
 */
public class LoanBackByFundDTO {

  private String contractId = "";

  private String borrowerName = "";

  private String bizDate = "";

  private String pickMoney = "";

  private Integer orgId;

  private String orgName = "";

  private Integer kouEmpId;

  private String kouEmpName = "";

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
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

  public String getKouEmpName() {
    return kouEmpName;
  }

  public void setKouEmpName(String kouEmpName) {
    this.kouEmpName = kouEmpName;
  }

  public Integer getKouEmpId() {
    return kouEmpId;
  }

  public void setKouEmpId(Integer kouEmpId) {
    this.kouEmpId = kouEmpId;
  }

  public Integer getOrgId() {
    return orgId;
  }

  public void setOrgId(Integer orgId) {
    this.orgId = orgId;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getPickMoney() {
    return pickMoney;
  }

  public void setPickMoney(String pickMoney) {
    this.pickMoney = pickMoney;
  }

}
