package org.xpup.hafmis.sysloan.accounthandle.dayclear.business;

import java.util.ArrayList;
import java.util.List;

import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.domain.HafEmployee;
import org.xpup.hafmis.sysloan.accounthandle.dayclear.bsinterface.IDayclearBS;


/**
 * @author ЭѕСт 2007-11-16
 */
public class DayclearBS implements IDayclearBS {

  private SecurityDAO securityDAO=null;

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }
  
  public String getBizDate(final String username,final String userIp ){
    HafEmployee hafEmployee=securityDAO.getUserInfo(username, userIp);
    return hafEmployee.getPlbizDate();
  }
  public List getBankInfoList(String username){
    List list = new ArrayList();
    list = securityDAO.getLoanBankDateList_jj(username);
    return list;
  }
}
