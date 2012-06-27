package org.xpup.hafmis.syscollection.querystatistics.paymentpickdetail.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickdetail.form.PaymntPickAF;

public interface IPaymntPickBS {
  /*
   * 查询缴存支取明细
   */
  public PaymntPickAF queryPaymntPickDetail(Pagination pagination, SecurityInfo securityInfo) 
    throws Exception, BusinessException;
  /*
   * 查询缴存支取按月统计信息
   */
  public PaymntPickAF queryPaymntPickMonthRept(Pagination pagination, SecurityInfo securityInfo) 
    throws Exception, BusinessException;
}
