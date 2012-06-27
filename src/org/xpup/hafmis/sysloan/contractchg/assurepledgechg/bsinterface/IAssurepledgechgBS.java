package org.xpup.hafmis.sysloan.contractchg.assurepledgechg.bsinterface;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTbAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTcAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTdAF;

public interface IAssurepledgechgBS {

  public List defaultQueryBorrowerAccList(Pagination pagination,SecurityInfo securityInfo) throws Exception ,BusinessException;
  
  public void addPledgeContract(String pkId,SecurityInfo securityInfo,EndorsecontractTbAF endorsecontractTbAF,HttpServletRequest request) throws Exception ,BusinessException;
  /**
   * TB ×÷·Ï°´Å¥
   * @param id
   * @param pagination
   * @param securityInfo
   * @param request
   * @throws Exception
   * @throws BusinessException
   */
  public void deletePledgeContract(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  
  public void addImpawnContract(String pkId,SecurityInfo securityInfo,EndorsecontractTcAF endorsecontractTcAF,HttpServletRequest request) throws Exception ,BusinessException;
  public void deleteImpawnContract(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  
  public void addAssurer(String pkId,SecurityInfo securityInfo,EndorsecontractTdAF endorsecontractTdAF,HttpServletRequest request) throws Exception ,BusinessException;
  public void deleteAssurer(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
}
