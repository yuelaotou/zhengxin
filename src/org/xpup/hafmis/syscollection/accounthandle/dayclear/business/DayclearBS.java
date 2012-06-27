package org.xpup.hafmis.syscollection.accounthandle.dayclear.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.HafEmployee;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.dayclear.bsinterface.IDayclearBS;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;


public class DayclearBS implements IDayclearBS {

  private SecurityDAO securityDAO=null;
  private LoanBankParaDAO loanBankParaDAO=null;

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }
  public String getBizDate(final String username,final String userIp ){
    HafEmployee hafEmployee=securityDAO.getUserInfo(username, userIp);
    return hafEmployee.getBizDate();
  }
  
  public List getBankInfoList(String username){
    List list = new ArrayList();
    list = securityDAO.getCollBankDateList_jj(username);
    return list;
  }
  
  public List getBankInfoList(String username,String opSystemType){
    List list = new ArrayList();
    if(opSystemType.equals(Integer
        .toString((BusiLogConst.OP_SYSTEM_TYPE_COLLECTION)))) {
      list = securityDAO.getCollBankDateList_jj(username);
    }else if(opSystemType.equals(Integer
        .toString((BusiLogConst.OP_SYSTEM_TYPE_LOAN)))) {
      list = securityDAO.getLoanBankDateList_jj(username);
    }
    return list;
  }
  /**
   * 日结
   * 
   * @param rowArray
   * @throws Exception
   * @throws BusinessException
   */
  public void updateBizDate_jj(String[] rowArray,SecurityInfo securityInfo) throws Exception,
      BusinessException {
    String str = "";
    String collBankId = "";
    List list = new ArrayList();
    List temp_officeList = new ArrayList();
    String bizDate = "";
    List officeList = securityInfo.getOfficeList();
    OfficeDto officedto = null;
    Iterator itr1 = officeList.iterator();
    while (itr1.hasNext()) {
      officedto = (OfficeDto) itr1.next();
      temp_officeList.add(officedto.getOfficeCode());
    }
    try {
      if (rowArray.length > 0) {
        for (int i = 0; i < rowArray.length; i++) {
          str = rowArray[i];
          if(str.indexOf(",")==-1){
          collBankId = str.substring(0, 2) ;
          bizDate = str.substring(2, str.length());
          }else{
          collBankId = str.substring(0, str.indexOf(","));
          bizDate = str.substring(str.indexOf(",") + 1, str.length());
          }
          // 判断该银行下是否有未记账的业务
          CollBank collBank = loanBankParaDAO
              .getCollBankByCollBankid_(collBankId);

          list = loanBankParaDAO.queryAA101Status_wsh(collBankId,temp_officeList,bizDate);
          if (list.size() > 0) {
            throw new BusinessException(collBank.getCollBankName()
                + "有未记账的业务不能日结！");
          }

        }
      }
      if (rowArray.length > 0) {
        for (int i = 0; i < rowArray.length; i++) {
          str = rowArray[i];
          if(str.indexOf(",")==-1){
            collBankId = str.substring(0, 2) ;
            bizDate = str.substring(2, str.length());
            }else{
            collBankId = str.substring(0, str.indexOf(","));
            bizDate = str.substring(str.indexOf(",") + 1, str.length());
            }
          securityDAO.updateCollBankDate_jj(collBankId, bizDate);
        }
      }
    } catch (BusinessException e) {
      throw new BusinessException(e.getLocalizedMessage());
    }catch (Exception e) {
e.printStackTrace();
    }
  }
  public void setLoanBankParaDAO(LoanBankParaDAO loanBankParaDAO) {
    this.loanBankParaDAO = loanBankParaDAO;
  }
}
