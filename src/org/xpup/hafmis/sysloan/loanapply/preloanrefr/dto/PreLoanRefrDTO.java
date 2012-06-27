/**
 * Copy Right Information   : Goldsoft 
 * Project                  : PreLoanRefrDTO
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2008-05-19
 **/
package org.xpup.hafmis.sysloan.loanapply.preloanrefr.dto;

import java.math.BigDecimal;

public class PreLoanRefrDTO {

  private String loanMoney = null;
  
  private Integer yearlimit = new Integer(0);// 贷款期限（年限）

  private BigDecimal corpusInterest = new BigDecimal(0.00);// 月还款额(月还本息)
  
  private BigDecimal loanmoneyTotal = new BigDecimal(0.00);// 还款总额
  
  private BigDecimal interestTotal = new BigDecimal(0.00);// 利息总额
  
 
  
  private String rate = "";// 年利率

  public BigDecimal getCorpusInterest() {
    return corpusInterest;
  }

  public void setCorpusInterest(BigDecimal corpusInterest) {
    this.corpusInterest = corpusInterest;
  }

  public BigDecimal getInterestTotal() {
    return interestTotal;
  }

  public void setInterestTotal(BigDecimal interestTotal) {
    this.interestTotal = interestTotal;
  }

  public String getLoanMoney() {
    return loanMoney;
  }

  public void setLoanMoney(String loanMoney) {
    this.loanMoney = loanMoney;
  }

  public BigDecimal getLoanmoneyTotal() {
    return loanmoneyTotal;
  }

  public void setLoanmoneyTotal(BigDecimal loanmoneyTotal) {
    this.loanmoneyTotal = loanmoneyTotal;
  }

  public String getRate() {
    return rate;
  }

  public void setRate(String rate) {
    this.rate = rate;
  }

  public Integer getYearlimit() {
    return yearlimit;
  }

  public void setYearlimit(Integer yearlimit) {
    this.yearlimit = yearlimit;
  }
}
