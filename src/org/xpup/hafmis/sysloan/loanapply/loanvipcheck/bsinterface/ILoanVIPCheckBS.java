package org.xpup.hafmis.sysloan.loanapply.loanvipcheck.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.loanvipcheck.form.LoanVIPCheckShowAF;

/**
 * @author 王野 2007-09-27
 */
public interface ILoanVIPCheckBS {

  // 审批贷款列表
  public LoanVIPCheckShowAF queryBorrowerListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException;

  // 审批通过 update BorrowerAcc
  public void updateContractSTApprovalPass(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 终审通过 update BorrowerAcc
  public void updateContractSTlastsure(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 撤消终审通过 update BorrowerAcc
  public void updateContractSTdellastsure(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  //审批不通过 update BorrowerAcc
  public void updateContractSTApprovalNotPass(String contractId, String reason,
      SecurityInfo securityInfo) throws Exception, BusinessException;
  // 审批不通过 update BorrowerAcc
  public void updateContractSTApprovalNotPass(String[] rowArray, String reason,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 撤销审批 update BorrowerAcc
  public void updateContractSTApprovalQuash(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 转换办事处
  public String changeOffice(String office) throws Exception;

  // 再次审批通过
  public void updateContractStApprovalPassAgain(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 撤消再次审批
  public void updateContractStApprovalQuashAgain(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;
}
