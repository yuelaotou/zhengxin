package org.xpup.hafmis.sysloan.common.domain.entity;

import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public class LoanRateType extends DomainObject {

  /** 利率类型 **/
  private String loanRateType;
  /** 利率说明 **/
  private String loanRateExplain;
  /** 利率时间 **/
  private String loanRateDate;
  
  private String reserveA;
  
  private String reserveB;
  
  private String reserveC;

  public String getLoanRateDate() {
    return loanRateDate;
  }

  public void setLoanRateDate(String loanRateDate) {
    this.loanRateDate = loanRateDate;
  }

  public String getLoanRateExplain() {
    return loanRateExplain;
  }

  public void setLoanRateExplain(String loanRateExplain) {
    this.loanRateExplain = loanRateExplain;
  }

  public String getLoanRateType() {
    return loanRateType;
  }

  public void setLoanRateType(String loanRateType) {
    this.loanRateType = loanRateType;
  }

  public String getReserveA() {
    return reserveA;
  }

  public void setReserveA(String reserveA) {
    this.reserveA = reserveA;
  }

  public String getReserveB() {
    return reserveB;
  }

  public void setReserveB(String reserveB) {
    this.reserveB = reserveB;
  }

  public String getReserveC() {
    return reserveC;
  }

  public void setReserveC(String reserveC) {
    this.reserveC = reserveC;
  }
}
