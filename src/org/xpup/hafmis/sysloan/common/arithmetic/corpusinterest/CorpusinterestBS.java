package org.xpup.hafmis.sysloan.common.arithmetic.corpusinterest;

import java.math.BigDecimal;

public abstract class CorpusinterestBS {

  /**
   * 月还本息
   * 
   * @param overplusLoanMoney 剩余本金
   * @param loanRate 月利率
   * @param timelimit 剩余期限
   * @return
   */
  public static BigDecimal getCorpusInterest(BigDecimal overplusLoanMoney,
      BigDecimal loanRate, String timelimit) {
    // 月还本息=剩余本金*（1+月利率）^剩余期限*月利率/(1+月利率)^剩余期限-1
    BigDecimal corpusInterest = new BigDecimal(0.00);
    try {
      if (overplusLoanMoney.doubleValue() > 0) {
        BigDecimal temp_loanRate = new BigDecimal(1.00).add(loanRate);// （1+月利率）
        BigDecimal tempMoney = new BigDecimal(Math.pow(temp_loanRate
            .doubleValue(), Double.parseDouble(timelimit)));// （1+月利率）^剩余期限
        BigDecimal temp = tempMoney.subtract(new BigDecimal(1.00));// (1+月利率)^剩余期限-1
        corpusInterest = overplusLoanMoney.multiply(tempMoney).multiply(
            loanRate).divide(temp, 2, BigDecimal.ROUND_HALF_UP);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return corpusInterest;
  }
}