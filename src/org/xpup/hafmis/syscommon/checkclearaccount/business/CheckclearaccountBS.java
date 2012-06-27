package org.xpup.hafmis.syscommon.checkclearaccount.business;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscommon.checkclearaccount.bsinterface.ICheckclearaccountBS;

/**
 * @author Õı¡· 2007-6-27
 */
public class CheckclearaccountBS implements ICheckclearaccountBS {

  private OrgHAFAccountFlowDAO orgHAFAccountFlowDAO = null;

  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
    this.orgHAFAccountFlowDAO = orgHAFAccountFlowDAO;
  }

  public String checkclearaccount(SecurityInfo securityInfo) throws Exception, BusinessException {
    String flag = "0";
    try { 
      String opsys=securityInfo.getOpSystemType();
      String oper=securityInfo.getUserInfo().getUsername();
      flag=orgHAFAccountFlowDAO.queryClearaccount_WL(opsys,oper,securityInfo);
    }catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    }catch(Exception e){
      e.printStackTrace();
    }
    return flag;
  }

}
