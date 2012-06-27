package org.xpup.hafmis.sysloan.common.arithmetic.entireyearmoney;



import java.math.BigDecimal;

public abstract class EntireYearMoneyBS {
  /**
   * 整年期总还款额
   * @param loanMoney  贷款金额
   * @param loanRate  月利率
   * @param loanTime  贷款期限
   * @return
   */
  public static BigDecimal getEntireYearMoney(BigDecimal loanMoney,BigDecimal loanRate,String loanTime){
    //整年期总还款额=贷款金额*（（贷款期限*月利率）+1）
    BigDecimal entireYearMoney = new BigDecimal(0.00);
    try{
      if(loanMoney.doubleValue()>0){
        BigDecimal temp = new BigDecimal(loanTime).multiply(loanRate).add(new BigDecimal(1.00));//  （贷款期限*月利率）+1
        entireYearMoney = loanMoney.multiply(temp).setScale(2, BigDecimal.ROUND_HALF_UP);
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return entireYearMoney;
  }
}
