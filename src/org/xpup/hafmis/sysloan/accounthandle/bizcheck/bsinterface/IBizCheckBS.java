package org.xpup.hafmis.sysloan.accounthandle.bizcheck.bsinterface;

import javax.servlet.http.HttpServletRequest;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.form.BizCheckShowListAF;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.dto.LoanaccordDTO;
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto.OverPayDTO;
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto.BailDTO;
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto.AdjustAccountDTO;

/**
 * @author 吴迪 2007-09-2９
 */
public interface IBizCheckBS {
  // 账务处理—业务复核—业务复核列表
  public BizCheckShowListAF queryShowListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 账务处理—业务复核—复核通过
  public void updateBizSTcheckthrough(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 账务处理—业务复核—撤消复核
  public void updateBizSTdelcheck(String[] rowArray, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  // 账务处理—业务复核—批量撤消复核
  public void updateBizSTcheckall(SecurityInfo securityInfo,
      Pagination pagination) throws Exception, BusinessException;

  // 账务处理—业务复核—批量复核
  public void updateBizSTdelcheckall(SecurityInfo securityInfo,
      Pagination pagination) throws Exception, BusinessException;

  // 账务处理—业务复核—发放信息
  public LoanaccordDTO queryLoanaccordById(String flowHeadId,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 账务处理—业务复核—挂帐
  public OverPayDTO queryOverPayById(String flowHeadId,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 账务处理—业务复核—挂帐
  public BailDTO queryBailById(String flowHeadId, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  // 账务处理—业务复核—错帐调整
  public AdjustAccountDTO queryAdjustAccountById(String flowHeadId,
      SecurityInfo securityInfo) throws Exception, BusinessException;
}
