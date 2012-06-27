package org.xpup.hafmis.sysloan.dataready.loanpara.business;

import org.xpup.hafmis.syscollection.common.dao.CollParaDAO;
import org.xpup.hafmis.sysloan.dataready.loanpara.bsinterface.ILoanDocNumDesignBS;
/**
 * 
 * @author 杨光
 * 2008-5-21  
 */

public class LoanDocNumDesignBS implements ILoanDocNumDesignBS{

  private CollParaDAO collParaDAO=null;
  public CollParaDAO getCollParaDAO() {
    return collParaDAO;
  }

  public void setCollParaDAO(CollParaDAO collParaDAO) {
    this.collParaDAO = collParaDAO;
  }
  /**
   * 获得凭证号生成方式，中心还是银行
   * @author 杨光
   */
  public String getLoanDocNumDesignPara() throws Exception {
    String loanDocNumDocument="";
    loanDocNumDocument=collParaDAO.getLoanDocNumDesignPara();
    return loanDocNumDocument;
  }
  
  /**
   * 更新凭证号生成方式，中心还是银行
   * @author 
   */
  public void updateLoanDocNumDesignPara(String loanDocNumDocument) throws Exception {
    collParaDAO.updateLoanDocNumDesignPara(loanDocNumDocument);
  }
  public String getNamePara() throws Exception {
    String name="";
    name=collParaDAO.getLoanNamePara();
    return name;
  }
  public void updateNamePara(String name) throws Exception {
    collParaDAO.updateLoanNamePara(name);
  }
  
}
