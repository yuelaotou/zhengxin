package org.xpup.hafmis.sysfinance.common.biz.queryflow.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.form.OrgbusinessflowAF;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.form.LoanBusiFlowQueryAF;

public interface IQueryFlowBS {
  /**
   * 判断结算号属于那种业务
   * 
   * @param settNum 结算号
   * @return true(归集业务) false(个贷业务)
   * @throws Exception
   */
  public boolean IssettNum(String settNum) throws Exception;

  /**
   * 根据条件查询单位业务流水列表
   * 
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public OrgbusinessflowAF findOrgFlowListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  /**
   * Description 贷款业务流水统计查询列表
   * 
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public LoanBusiFlowQueryAF queryLoanBusiFlowQueryListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException;
}
