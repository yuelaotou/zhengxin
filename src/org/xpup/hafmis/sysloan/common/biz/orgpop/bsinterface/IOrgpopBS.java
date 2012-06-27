package org.xpup.hafmis.sysloan.common.biz.orgpop.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface IOrgpopBS{
  /**
   * 根据单位编号、单位名称、单位状态查询单位信息
   * @param pagination
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public List findOrgpopList(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  
}