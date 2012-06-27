package org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.form.BailClearInterestTaAF;
import org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.form.BailClearInterestTbAF;

/**
 * @author 王野 2007-10-05
 */
public interface IBailClearInterestBS {

  // 条件查询，通过放款银行
  public BailClearInterestTaAF findBailClearInterestTaListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException;

  // 办理保证金结息-全部结息
  public void bailClearInterestTa(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 保证金结息维护-显示列表
  public BailClearInterestTbAF queryBailClearInterestTbListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException;
}
