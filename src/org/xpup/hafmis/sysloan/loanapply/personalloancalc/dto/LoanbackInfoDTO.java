package org.xpup.hafmis.sysloan.loanapply.personalloancalc.dto;

import java.math.BigDecimal;

public class LoanbackInfoDTO {
  private BigDecimal corpus;

  private BigDecimal interest;

  private BigDecimal corpusInterest;

  private String loanTimeLimit;

  private BigDecimal overplusLoanMoney;

  public BigDecimal getCorpus() {
    return corpus;
  }

  public void setCorpus(BigDecimal corpus) {
    this.corpus = corpus;
  }

  public BigDecimal getCorpusInterest() {
    return corpusInterest;
  }

  public void setCorpusInterest(BigDecimal corpusInterest) {
    this.corpusInterest = corpusInterest;
  }

  public BigDecimal getInterest() {
    return interest;
  }

  public void setInterest(BigDecimal interest) {
    this.interest = interest;
  }

  public String getLoanTimeLimit() {
    return loanTimeLimit;
  }

  public void setLoanTimeLimit(String loanTimeLimit) {
    this.loanTimeLimit = loanTimeLimit;
  }

  public BigDecimal getOverplusLoanMoney() {
    return overplusLoanMoney;
  }

  public void setOverplusLoanMoney(BigDecimal overplusLoanMoney) {
    this.overplusLoanMoney = overplusLoanMoney;
  }

}
