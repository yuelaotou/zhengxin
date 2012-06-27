package org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface;

import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

/**
 * 
 * @author ั๎นโ
 * 2007-6-26  
 */
public interface ILoanDocNumDesignBS {
  public String getLoanDocNumDesignPara() throws Exception;
  public void updateLoanDocNumDesignPara(String docNumDocument) throws Exception;
  public String getNamePara() throws Exception;
  public void updateNamePara(String name) throws Exception;
  public String queryCollectionBankNameById(String id,SecurityInfo securityInfo) throws Exception;
  public String queryCollectionBankAccById(String id,SecurityInfo securityInfo) throws Exception;
  public String queryCollectionBankNameById_yg(String id) throws Exception;
}
