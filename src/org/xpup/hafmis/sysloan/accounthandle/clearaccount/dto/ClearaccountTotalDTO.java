package org.xpup.hafmis.sysloan.accounthandle.clearaccount.dto;

import java.math.BigDecimal;

public class ClearaccountTotalDTO {
  
  private BigDecimal occurMoneyTotle= new BigDecimal(0.00);// 发放金额-总额

  private BigDecimal reclaimCorpusTotle= new BigDecimal(0.00);// 回收本金-总额

  private BigDecimal reclaimAccrualTotle= new BigDecimal(0.00);// 回收利息-总额总额

  private BigDecimal realPunishInterestTotle= new BigDecimal(0.00);// 回收罚息-总额
  
  private BigDecimal badDebtTotle= new BigDecimal(0.00);// 呆账核销金额-总额

  private BigDecimal putUpMoneyTotle= new BigDecimal(0.00);// 挂账金额-总额

  private BigDecimal bailTotle= new BigDecimal(0.00);// 保证金-总额

  private BigDecimal bailAccrualTotle= new BigDecimal(0.00);// 保证金利息-总额
  
  private BigDecimal realCorpusTotal = new BigDecimal(0.00);//实还本金-总额
  
  private BigDecimal realInterestTotal = new BigDecimal(0.00);//实还利息-总额
  
  private BigDecimal realPunish_interestTotal = new BigDecimal(0.00);//实还罚息-总额
  
  private int affirmbizSt = 0;// 确认状态个数

  private int checkbizSt = 0;// 复合状态个数
  
  private int realcount=0;

  private int count = 0;

  public int getAffirmbizSt() {
    return affirmbizSt;
  }

  public void setAffirmbizSt(int affirmbizSt) {
    this.affirmbizSt = affirmbizSt;
  }

  public BigDecimal getBadDebtTotle() {
    return badDebtTotle;
  }

  public void setBadDebtTotle(BigDecimal badDebtTotle) {
    this.badDebtTotle = badDebtTotle;
  }

  public BigDecimal getBailAccrualTotle() {
    return bailAccrualTotle;
  }

  public void setBailAccrualTotle(BigDecimal bailAccrualTotle) {
    this.bailAccrualTotle = bailAccrualTotle;
  }

  public BigDecimal getBailTotle() {
    return bailTotle;
  }

  public void setBailTotle(BigDecimal bailTotle) {
    this.bailTotle = bailTotle;
  }

  public int getCheckbizSt() {
    return checkbizSt;
  }

  public void setCheckbizSt(int checkbizSt) {
    this.checkbizSt = checkbizSt;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public BigDecimal getOccurMoneyTotle() {
    return occurMoneyTotle;
  }

  public void setOccurMoneyTotle(BigDecimal occurMoneyTotle) {
    this.occurMoneyTotle = occurMoneyTotle;
  }

  public BigDecimal getPutUpMoneyTotle() {
    return putUpMoneyTotle;
  }

  public void setPutUpMoneyTotle(BigDecimal putUpMoneyTotle) {
    this.putUpMoneyTotle = putUpMoneyTotle;
  }

  public BigDecimal getRealPunishInterestTotle() {
    return realPunishInterestTotle;
  }

  public void setRealPunishInterestTotle(BigDecimal realPunishInterestTotle) {
    this.realPunishInterestTotle = realPunishInterestTotle;
  }

  public BigDecimal getReclaimAccrualTotle() {
    return reclaimAccrualTotle;
  }

  public void setReclaimAccrualTotle(BigDecimal reclaimAccrualTotle) {
    this.reclaimAccrualTotle = reclaimAccrualTotle;
  }

  public BigDecimal getReclaimCorpusTotle() {
    return reclaimCorpusTotle;
  }

  public void setReclaimCorpusTotle(BigDecimal reclaimCorpusTotle) {
    this.reclaimCorpusTotle = reclaimCorpusTotle;
  }

  public BigDecimal getRealCorpusTotal() {
    return realCorpusTotal;
  }

  public void setRealCorpusTotal(BigDecimal realCorpusTotal) {
    this.realCorpusTotal = realCorpusTotal;
  }

  public BigDecimal getRealInterestTotal() {
    return realInterestTotal;
  }

  public void setRealInterestTotal(BigDecimal realInterestTotal) {
    this.realInterestTotal = realInterestTotal;
  }

  public BigDecimal getRealPunish_interestTotal() {
    return realPunish_interestTotal;
  }

  public void setRealPunish_interestTotal(BigDecimal realPunish_interestTotal) {
    this.realPunish_interestTotal = realPunish_interestTotal;
  }

  public int getRealcount() {
    return realcount;
  }

  public void setRealcount(int realcount) {
    this.realcount = realcount;
  }


}
