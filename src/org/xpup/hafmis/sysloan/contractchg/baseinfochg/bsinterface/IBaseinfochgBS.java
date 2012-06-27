package org.xpup.hafmis.sysloan.contractchg.baseinfochg.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTbNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTcNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTeNewAF;

public interface IBaseinfochgBS {

  LoanapplyTeNewAF showBaseinfochgTa(Pagination pagination,
      SecurityInfo securityInfo) throws Exception;

  LoanapplyNewAF showBaseinfochgTb(String contranctid) throws Exception;

  void updateBorrowerInfo(String contranctid, LoanapplyNewAF loanapplyNewAF,
      SecurityInfo securityInfo) throws Exception;

  LoanapplyTbNewAF findAssistanBorrowerInfochg(String contranctid,
      String maxauxiliaryid) throws Exception;

  public LoanapplyTbNewAF findAssistanBorrowerInfo_yg(String contractid, String auxiliaryid,String sun) throws BusinessException;

  LoanapplyTbNewAF findAssistantBorrowerInfo(String empId, String orgId,
      String contractaId) throws BusinessException;

  void addupdateAssistantBorrowerInfo(LoanapplyTbNewAF loanapplytbnewAF,
      SecurityInfo securityInfo, String temp_addtypechg)
      throws BusinessException, Exception;

  void canceledAssistanBorrower(String id, SecurityInfo securityInfo)
      throws BusinessException;

  LoanapplyTcNewAF showHouseInfo(String contranctid) throws Exception;

  void updateHousesInfo(LoanapplyTcNewAF loanapplytcnewAF,
      SecurityInfo securityInfo) throws Exception;

}
