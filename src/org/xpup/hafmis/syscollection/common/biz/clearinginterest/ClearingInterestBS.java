package org.xpup.hafmis.syscollection.common.biz.clearinginterest;

import java.math.BigDecimal;

import org.xpup.hafmis.syscollection.dto.ClearingInterestDTO;
import org.xpup.hafmis.syscollection.dto.InterestDto;

/**
 * @author 王玲
 * 2007-6-26
 * 该方法用于实现计算年终利息（本年利息、往年利息）
 */
public class ClearingInterestBS implements ClearingInterestInterface {
  
  public InterestDto getClearinginterest(ClearingInterestDTO clearingInterestDTO){
    
    InterestDto interestDto = new InterestDto();
    
    BigDecimal  curInterest = new BigDecimal(0.00);//本年利息
    BigDecimal  preInterest = new BigDecimal(0.00);//往年利息

    BigDecimal TEMP_curIntegral = clearingInterestDTO.getCurIntegral();//临时本年积数
    BigDecimal TEMP_preIntegral = clearingInterestDTO.getPreIntegral();//临时往年积数
    BigDecimal TEMP_curRate = clearingInterestDTO.getCurRate();//临时本年利率
    BigDecimal TEMP_preRate = clearingInterestDTO.getPreRate();//临时往年利率
    
    //本年利息=本年积数*本年利率/365、往年利息=往年积数*往年利率/365
    curInterest = curInterest.add(TEMP_curIntegral.multiply(TEMP_curRate).divide(new BigDecimal(365),2,BigDecimal.ROUND_HALF_UP));//存本年利息
    preInterest = preInterest.add(TEMP_preIntegral.multiply(TEMP_preRate).divide(new BigDecimal(365),2,BigDecimal.ROUND_HALF_UP));//存往年利息
    
    BigDecimal  TEMP_curIntegealSubA = clearingInterestDTO.getCurIntegealSubA();//临时分段本年积数a
    BigDecimal  TEMP_preIntegealSubA = clearingInterestDTO.getPreIntegealSubA();//临时分段往年积数a
    BigDecimal  TEMP_curRateA = clearingInterestDTO.getCurRateA();//临时本年利率a
    BigDecimal  TEMP_preRateA = clearingInterestDTO.getPreRateA();//临时往年利率a
    
    BigDecimal  TEMP_curIntegealSubB = clearingInterestDTO.getCurIntegealSubB();//临时分段本年积数b
    BigDecimal  TEMP_preIntegealSubB = clearingInterestDTO.getPreIntegealSubB();//临时分段往年积数b
    BigDecimal  TEMP_curRateB = clearingInterestDTO.getCurRateB();//临时本年利率b
    BigDecimal  TEMP_preRateB = clearingInterestDTO.getPreRateB();//临时往年利率b
    
    BigDecimal  TEMP_curIntegealSubC = clearingInterestDTO.getCurIntegealSubC();//临时分段本年积数c
    BigDecimal  TEMP_preIntegealSubC = clearingInterestDTO.getPreIntegealSubC();//临时分段往年积数c
    BigDecimal  TEMP_curRateC = clearingInterestDTO.getCurRateC();//临时本年利率c
    BigDecimal  TEMP_preRateC = clearingInterestDTO.getPreRateC();//临时往年利率c
    
    
    if(!TEMP_curRateA.equals(new BigDecimal(0))){//如果本年利率a!=0:本年积数=分段本年积数a、往年积数=分段往年积数a、本年利息=本年积数*本年利率a/365、往年利息=往年积数*往年利率a/365
      
      curInterest = curInterest.add(TEMP_curIntegealSubA.multiply(TEMP_curRateA).divide(new BigDecimal(365),2,BigDecimal.ROUND_HALF_UP));//存本年利息
      preInterest = preInterest.add(TEMP_preIntegealSubA.multiply(TEMP_preRateA).divide(new BigDecimal(365),2,BigDecimal.ROUND_HALF_UP));//存往年利息
      
      if(!TEMP_curRateB.equals(new BigDecimal(0))){//如果本年利率b!=0则插入一条记录本年积数=分段本年积数b、往年积数=分段往年积数b、本年利息=本年积数*本年利率b/365、往年利息=往年积数*往年利率b/365
       
        curInterest = curInterest.add(TEMP_curIntegealSubB.multiply(TEMP_curRateB).divide(new BigDecimal(365),2,BigDecimal.ROUND_HALF_UP));//存本年利息
        preInterest = preInterest.add(TEMP_preIntegealSubB.multiply(TEMP_preRateB).divide(new BigDecimal(365),2,BigDecimal.ROUND_HALF_UP));//存往年利息
       
        if(!TEMP_curRateC.equals(new BigDecimal(0))){//如果本年利率c!=0则插入一条记录本年积数=分段本年积数c、往年积数=分段往年积数c、本年利息=本年积数*本年利率c/365、往年利息=往年积数*往年利率c/365
          
          curInterest = curInterest.add(TEMP_curIntegealSubC.multiply(TEMP_curRateC).divide(new BigDecimal(365),2,BigDecimal.ROUND_HALF_UP));//存本年利息
          preInterest = preInterest.add(TEMP_preIntegealSubC.multiply(TEMP_preRateC).divide(new BigDecimal(365),2,BigDecimal.ROUND_HALF_UP));//存往年利息
        }
      }
    }
    
    interestDto.setCurInterest(curInterest);
    interestDto.setPreInterest(preInterest);
    
    return interestDto;
  }

}
