package org.xpup.hafmis.syscollection.paymng.paysure.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

/**
 * 
 * @author 吴洪涛
 * 2007-6-26  
 */
public interface IDocNumBS {
  
  public String getDocNumDesignPara() throws Exception;
  //根据条件officeCode与bizYearmonth生成凭证号
  public String getDocNumdocNum(String officeCode,String bizYearmonth,SecurityInfo securityInfo) throws Exception,BusinessException;
  //添加记录
  public void insertDocNumCancel(String docNum,String officeCode,String bizYearmonth) throws Exception, BusinessException;
}
