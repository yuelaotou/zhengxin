package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgbaseinfo.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;

public interface IOrgBaseInfoBS {
  public List findOrgList(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  public Org findOrgInfoById(Integer orgId)throws BusinessException;
  
  public List findOrgAllList(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException; // 打印列表的List
}
