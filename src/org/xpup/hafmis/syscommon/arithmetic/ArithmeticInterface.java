package org.xpup.hafmis.syscommon.arithmetic;

import java.math.BigDecimal;

import org.xpup.common.exception.BusinessException;

public interface ArithmeticInterface{
  //根据工资基数和缴率按相应的缴存精度返回缴额
  public BigDecimal getPay(BigDecimal salaryBase,BigDecimal rate) ;
  //根据缴额按相应的缴存精度返回缴额
  public BigDecimal getPay(BigDecimal pay) ;
  
}