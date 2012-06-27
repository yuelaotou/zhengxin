package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearstatistics.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearstatistics.dto.PaymentyearstatisticsDTO;

public interface IPaymentyearstatisticsBS {
//市本级
  public PaymentyearstatisticsDTO queryPaymentyearstatisticsDTO1(Pagination pagination,SecurityInfo securityInfo)throws Exception,BusinessException;
//大石桥
  public PaymentyearstatisticsDTO queryPaymentyearstatisticsDTO2(Pagination pagination,SecurityInfo securityInfo)throws Exception,BusinessException;
  //盖州
  public PaymentyearstatisticsDTO queryPaymentyearstatisticsDTO3(Pagination pagination,SecurityInfo securityInfo)throws Exception,BusinessException;
  //鲅鱼圈
  public PaymentyearstatisticsDTO queryPaymentyearstatisticsDTO4(Pagination pagination,SecurityInfo securityInfo)throws Exception,BusinessException;
}
