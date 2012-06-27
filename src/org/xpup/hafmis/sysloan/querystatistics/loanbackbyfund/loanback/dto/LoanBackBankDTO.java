package org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanback.dto;

import java.math.BigDecimal;

public class LoanBackBankDTO {

  private String collbankname = "";

  private BigDecimal corpus = new BigDecimal(0.00);

  private BigDecimal interest = new BigDecimal(0.00);

  private BigDecimal corpusInterest = new BigDecimal(0.00);

  private BigDecimal all = new BigDecimal(0.00);

  private int count = 0;

  public BigDecimal getAll() {
    return all;
  }

  public void setAll(BigDecimal all) {
    this.all = all;
  }

  public String getCollbankname() {
    return collbankname;
  }

  public void setCollbankname(String collbankname) {
    this.collbankname = collbankname;
  }

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

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public BigDecimal getInterest() {
    return interest;
  }

  public void setInterest(BigDecimal interest) {
    this.interest = interest;
  }
}
