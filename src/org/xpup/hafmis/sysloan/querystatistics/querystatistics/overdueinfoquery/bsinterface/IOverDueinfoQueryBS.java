package org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.dto.OverDueinfoQueryShowListDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.form.OverDueinfoQueryShowListAF;

public interface IOverDueinfoQueryBS {
  /**
   * 统计查询-逾期催还查询信息列表
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public OverDueinfoQueryShowListAF queryShowListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;
  /**
   * 统计查询-逾期催还查询信息列表打印
   * @param pagination  
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  
  public List findoverDueinfoQueryPrint(Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException;
 
  /**
   * 统计查询-逾期催还查询信息单笔打印
   * @param contractId
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  
  public OverDueinfoQueryShowListDTO findoverDueinfoQueryPrintone(String  id,SecurityInfo securityInfo) throws Exception, BusinessException;
  public void createOverdueData(SecurityInfo securityInfo,String loanBankId) throws Exception;
  public void importOverdueData(List importList, SecurityInfo securityInfo)throws Exception;
 
}
