package org.xpup.hafmis.syscollection.common.biz.loankoupop.dto;

import java.math.BigDecimal;

public class LoanKouPopDTO {
  private String id = "";// 主键

  private String empId = "";// 职工编号

  private String orgId = "";// 单位编号

  private BigDecimal kouPreBalance = new BigDecimal(0.00);// 扣往年金额

  private BigDecimal kouCurBalance = new BigDecimal(0.00);// 扣本年金额
  
  private BigDecimal kouBalance = new BigDecimal(0.00);//扣往年金额+扣本年金额

  private String contractId = "";// 合同编号

  private String loanKouAcc = "";// 贷款账号

  private BigDecimal shouldKouBalance = new BigDecimal(0.00);// 应扣金额

  public BigDecimal getKouCurBalance() {
    return kouCurBalance;
  }

  public void setKouCurBalance(BigDecimal kouCurBalance) {
    this.kouCurBalance = kouCurBalance;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public BigDecimal getKouPreBalance() {
    return kouPreBalance;
  }

  public void setKouPreBalance(BigDecimal kouPreBalance) {
    this.kouPreBalance = kouPreBalance;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
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

  public BigDecimal getShouldKouBalance() {
    return shouldKouBalance;
  }

  public void setShouldKouBalance(BigDecimal shouldKouBalance) {
    this.shouldKouBalance = shouldKouBalance;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public BigDecimal getKouBalance() {
    return kouBalance;
  }

  public void setKouBalance(BigDecimal kouBalance) {
    this.kouBalance = kouBalance;
  }
}
