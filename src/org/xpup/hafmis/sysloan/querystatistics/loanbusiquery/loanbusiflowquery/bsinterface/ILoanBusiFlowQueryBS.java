package org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.dto.LoanBusiFlowQueryClearDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.form.LoanBusiFlowQueryAF;
import org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.form.BailClearInterestTbAF;

/**
 * @author 王野 2007-10-15
 */
public interface ILoanBusiFlowQueryBS {

  // 贷款业务流水统计查询列表
  public LoanBusiFlowQueryAF queryLoanBusiFlowQueryListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException;

  // 弹出窗口——业务类型为结转余额
  public LoanBusiFlowQueryClearDTO queryLoanBusiFlowQueryClearByFlowHeadId(
      String flowHeadId, SecurityInfo securityInfo) throws Exception,
      BusinessException;
  
  // 弹出窗口——业务类型为结息
  public BailClearInterestTbAF queryLoanBusiFlowQueryBailClearListByFlowHeadId(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException;
}
