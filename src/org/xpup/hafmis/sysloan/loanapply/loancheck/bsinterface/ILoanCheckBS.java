package org.xpup.hafmis.sysloan.loanapply.loancheck.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.loancheck.form.LoanCheckShowAF;

/**
 * @author 王野 2007-09-22
 */
public interface ILoanCheckBS {

  // 审核贷款列表
  public LoanCheckShowAF queryBorrowerListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 审核通过 update BorrowerAcc
  public void updateContractSTCheckPass(String contractId,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  public void updateContractSTCheckPassrowArray(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 审核不通过 update BorrowerAcc
  public void updateContractSTCheckNotPass(String contractId, String reason,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  public void updateContractSTCheckNotPassrowArray(String[] rowArray,
      String reason, SecurityInfo securityInfo) throws Exception,
      BusinessException;

  // 撤销审核 update BorrowerAcc
  public void updateContractSTCheckQuash(String contractId,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  public void updateContractSTCheckQuashrowArray(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 转换办事处
  public String changeOffice(String office) throws Exception;
  // 回件确认
  public void updateContractStRedateSure(String contractId,
      SecurityInfo securityInfo) throws Exception, BusinessException;
  public void updateContractStRedateSureDel(String contractId,
      SecurityInfo securityInfo) throws Exception, BusinessException;
  // 再次审核通过
  public void updateContractStChkAgainPass(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 撤消再次审核
  public void updateContractStChkAgainQuash(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;
  
}
