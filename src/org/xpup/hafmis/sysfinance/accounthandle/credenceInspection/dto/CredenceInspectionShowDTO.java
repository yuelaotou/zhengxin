package org.xpup.hafmis.sysfinance.accounthandle.credenceInspection.dto;

import java.math.BigDecimal;

public class CredenceInspectionShowDTO {
  private BigDecimal debit = new BigDecimal(0.00);

  private BigDecimal credit = new BigDecimal(0.00);

  public BigDecimal getCredit() {
    return credit;
  }

  public void setCredit(BigDecimal credit) {
    this.credit = credit;
  }

  public BigDecimal getDebit() {
    return debit;
  }

  public void setDebit(BigDecimal debit) {
    this.debit = debit;
  }
}
