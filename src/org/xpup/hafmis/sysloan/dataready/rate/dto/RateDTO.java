package org.xpup.hafmis.sysloan.dataready.rate.dto;

import java.math.BigDecimal;

public class RateDTO {
  private Integer id;
  private String office;
  private String loanRateType;
  private BigDecimal loanMonthRate=new BigDecimal(0.00);
  private String ajustDate;
  private String appDate;
  private String status;
  private String adjustBasis;
  public String getAdjustBasis() {
    return adjustBasis;
  }
  public void setAdjustBasis(String adjustBasis) {
    this.adjustBasis = adjustBasis;
  }
  public String getAjustDate() {
    return ajustDate;
  }
  public void setAjustDate(String ajustDate) {
    this.ajustDate = ajustDate;
  }
  public String getAppDate() {
    return appDate;
  }
  public void setAppDate(String appDate) {
    this.appDate = appDate;
  }
  public BigDecimal getLoanMonthRate() {
    return loanMonthRate;
  }
  public void setLoanMonthRate(BigDecimal loanMonthRate) {
    this.loanMonthRate = loanMonthRate;
  }
  public String getLoanRateType() {
    return loanRateType;
  }
  public void setLoanRateType(String loanRateType) {
    this.loanRateType = loanRateType;
  }
  public String getOffice() {
    return office;
  }
  public void setOffice(String office) {
    this.office = office;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
}
