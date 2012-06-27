package org.xpup.hafmis.syscommon.arithmetic;

import java.math.BigDecimal;

/**
 * 四舍五入到分
 * 
 *@author 李娟
 *2007-6-21
 */
public class RounttoCentDAO implements ArithmeticInterface{

  /** 根据工资基数和缴率按相应的缴存精度返回缴额
   * salaryBase 工资基数
   * rate 缴率
   */
  public BigDecimal getPay(BigDecimal salaryBase, BigDecimal rate) {
    // TODO Auto-generated method stub
    BigDecimal pay = new BigDecimal(0.00);
    pay = salaryBase.multiply(rate);
    pay = pay.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_UP);
    return pay;
  }
  
  /**根据缴额按相应的缴存精度返回缴额
   * pay 缴额
   */
  public BigDecimal getPay(BigDecimal pay) {
    // TODO Auto-generated method stub
    pay = pay.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_UP);
    return pay;
  }
  
}