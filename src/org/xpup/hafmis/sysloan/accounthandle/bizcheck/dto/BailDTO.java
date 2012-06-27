package org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto;

import java.math.BigDecimal;

public class BailDTO {
  private String flowHeadId = ""; // pl202头表Id

  private String loankouacc = "";// 贷款账号

  private String contractId = "";// 合同编号

  private String borrowerName = "";// 借款人姓名

  private BigDecimal bailMoney = new BigDecimal(0.00);// 保证金收提金额

  private BigDecimal accrual = new BigDecimal(0.00);// 提取利息

  private BigDecimal overpusLoanMoney = new BigDecimal(0.00);// 贷款余额

  private BigDecimal noBackMoney = new BigDecimal(0.00);// 呆账未收回金额

  private BigDecimal ovaerLoanRepay = new BigDecimal(0.00);// 挂账余额

  public BigDecimal getAccrual() {
    return accrual;
  }

  public void setAccrual(BigDecimal accrual) {
    this.accrual = accrual;
  }

  public BigDecimal getBailMoney() {
    return bailMoney;
  }

  public void setBailMoney(BigDecimal bailMoney) {
    this.bailMoney = bailMoney;
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

  public String getLoankouacc() {
    return loankouacc;
  }

  public void setLoankouacc(String loankouacc) {
    this.loankouacc = loankouacc;
  }

  public BigDecimal getNoBackMoney() {
    return noBackMoney;
  }

  public void setNoBackMoney(BigDecimal noBackMoney) {
    this.noBackMoney = noBackMoney;
  }

  public BigDecimal getOvaerLoanRepay() {
    return ovaerLoanRepay;
  }

  public void setOvaerLoanRepay(BigDecimal ovaerLoanRepay) {
    this.ovaerLoanRepay = ovaerLoanRepay;
  }

  public BigDecimal getOverpusLoanMoney() {
    return overpusLoanMoney;
  }

  public void setOverpusLoanMoney(BigDecimal overpusLoanMoney) {
    this.overpusLoanMoney = overpusLoanMoney;
  }

  public String getFlowHeadId() {
    return flowHeadId;
  }

  public void setFlowHeadId(String flowHeadId) {
    this.flowHeadId = flowHeadId;
  }

}
