package org.xpup.hafmis.sysloan.loanapply.beforeloanapply.dto;

import java.math.BigDecimal;

public class BeforeLoanApplyDTO {
  private BigDecimal shouldCorpus = new BigDecimal(0.00);

  private BigDecimal shouldInterest = new BigDecimal(0.00);

  private BigDecimal monthBackMoney = new BigDecimal(0.00);

  private String payMonth = "";

  public BigDecimal getMonthBackMoney() {
    return monthBackMoney;
  }

  public void setMonthBackMoney(BigDecimal monthBackMoney) {
    this.monthBackMoney = monthBackMoney;
  }

  public String getPayMonth() {
    return payMonth;
  }

  public void setPayMonth(String payMonth) {
    this.payMonth = payMonth;
  }

  public BigDecimal getShouldCorpus() {
    return shouldCorpus;
  }

  public void setShouldCorpus(BigDecimal shouldCorpus) {
    this.shouldCorpus = shouldCorpus;
  }

  public BigDecimal getShouldInterest() {
    return shouldInterest;
  }

  public void setShouldInterest(BigDecimal shouldInterest) {
    this.shouldInterest = shouldInterest;
  }
}
