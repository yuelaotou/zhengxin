package org.xpup.hafmis.syscollection.systemmaintain.loanpara.business;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.CollParaDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;

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
  
  private OrgDAO orgDAO=null;  
  
  private EmpDAO empDAO=null;  
  
  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }
  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }
  /**
   * 获得凭证号生成方式，中心还是银行
   * @author 杨光
   */
  public String getLoanDocNumDesignPara() throws Exception {
    String loanDocNumDocument="";
    loanDocNumDocument=collParaDAO.getDocNumDesignPara();
    return loanDocNumDocument;
  }
  
  /**
   * 更新凭证号生成方式，中心还是银行
   * @author 
   */
  public void updateLoanDocNumDesignPara(String loanDocNumDocument) throws Exception {
    collParaDAO.updateDocNumDesignPara(loanDocNumDocument);
  }
  public String getNamePara() throws Exception {
    String name="";
    name=collParaDAO.getNamePara();
    return name;
  }
  public void updateNamePara(String name) throws Exception {
    collParaDAO.updateNamePara(name);
  }
//吴洪涛 2008-6-16//查询出银行名称
  public String queryCollectionBankNameById(String id, SecurityInfo securityInfo)
      throws Exception {
    // TODO Auto-generated method stub
    CollBank collBank = new CollBank();
    try {
      // 单位是否存在
      Org org = null;
      org = orgDAO.queryByCriterions(id, null, null, securityInfo);

      if (org == null) {
        throw new BusinessException(" 不存在该单位或单位不在权限范围之内！！");
      }
      String collectionBankId = org.getOrgInfo().getCollectionBankId();
      if (collectionBankId != null && !collectionBankId.equals("")) {
        collBank = empDAO.getCollBankByCollBankid(collectionBankId);
      }
      if (collBank == null) {
        collBank = new CollBank();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return collBank.getCollBankName();
  }
  public String queryCollectionBankNameById_yg(String id)
  throws Exception {
    CollBank collBank = new CollBank();
    try {
      // 单位是否存在
      Org org = null;
      org = orgDAO.queryById(new Integer(id));
      
      if (org == null) {
        throw new BusinessException(" 不存在该单位！！");
      }
      String collectionBankId = org.getOrgInfo().getCollectionBankId();
      if (collectionBankId != null && !collectionBankId.equals("")) {
        collBank = empDAO.getCollBankByCollBankid(collectionBankId);
      }
      if (collBank == null) {
        collBank = new CollBank();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return collBank.getCollBankName();
  }
  public String queryCollectionBankAccById(String id, SecurityInfo securityInfo)
  throws Exception {
    // TODO Auto-generated method stub
    CollBank collBank = new CollBank();
    try {
      // 单位是否存在
      Org org = null;
      org = orgDAO.queryByCriterions(id, null, null, securityInfo);
      
      if (org == null) {
        throw new BusinessException(" 不存在该单位或单位不在权限范围之内！！");
      }
      String collectionBankId = org.getOrgInfo().getCollectionBankId();
      if (collectionBankId != null && !collectionBankId.equals("")) {
        collBank = empDAO.getCollBankByCollBankid(collectionBankId);
      }
      if (collBank == null) {
        collBank = new CollBank();
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return collBank.getCollectionbankacc();
  }
 
}
