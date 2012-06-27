package org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.dto;

import java.math.BigDecimal;

public class DepositCheckAccWindowDTO {
  private BigDecimal bdcBalanceSum=new BigDecimal(0.00);//银行日记账余额
  private BigDecimal bcaBalanceSum=new BigDecimal(0.00);//银行对账单余额
  private BigDecimal bankMoneySum=new BigDecimal(0.00);//银行已收中心未收金额总计
  private BigDecimal officeMoneySum=new BigDecimal(0.00);//中心已收银行未收金额总计
  private BigDecimal bankOutMoneySum=new BigDecimal(0.00);//银行已付中心未付金额总计
  private BigDecimal officeOutMoneySum=new BigDecimal(0.00);//中心已付银行未付金额总计
  private BigDecimal adjustOfficeBalance=new BigDecimal(0.00);//调节后余额（中心）
  private BigDecimal adjustBankBalance=new BigDecimal(0.00);//调节后余额（银行）
  public BigDecimal getAdjustBankBalance() {
    return adjustBankBalance;
  }
  public void setAdjustBankBalance(BigDecimal adjustBankBalance) {
    this.adjustBankBalance = adjustBankBalance;
  }
  public BigDecimal getAdjustOfficeBalance() {
    return adjustOfficeBalance;
  }
  public void setAdjustOfficeBalance(BigDecimal adjustOfficeBalance) {
    this.adjustOfficeBalance = adjustOfficeBalance;
  }
  public BigDecimal getBankMoneySum() {
    return bankMoneySum;
  }
  public void setBankMoneySum(BigDecimal bankMoneySum) {
    this.bankMoneySum = bankMoneySum;
  }
  public BigDecimal getBankOutMoneySum() {
    return bankOutMoneySum;
  }
  public void setBankOutMoneySum(BigDecimal bankOutMoneySum) {
    this.bankOutMoneySum = bankOutMoneySum;
  }
  public BigDecimal getBcaBalanceSum() {
    return bcaBalanceSum;
  }
  public void setBcaBalanceSum(BigDecimal bcaBalanceSum) {
    this.bcaBalanceSum = bcaBalanceSum;
  }
  public BigDecimal getBdcBalanceSum() {
    return bdcBalanceSum;
  }
  public void setBdcBalanceSum(BigDecimal bdcBalanceSum) {
    this.bdcBalanceSum = bdcBalanceSum;
  }
  public BigDecimal getOfficeMoneySum() {
    return officeMoneySum;
  }
  public void setOfficeMoneySum(BigDecimal officeMoneySum) {
    this.officeMoneySum = officeMoneySum;
  }
  public BigDecimal getOfficeOutMoneySum() {
    return officeOutMoneySum;
  }
  public void setOfficeOutMoneySum(BigDecimal officeOutMoneySum) {
    this.officeOutMoneySum = officeOutMoneySum;
  }
}
