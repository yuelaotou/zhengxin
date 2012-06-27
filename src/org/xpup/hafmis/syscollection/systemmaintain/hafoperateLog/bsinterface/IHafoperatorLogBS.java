package org.xpup.hafmis.syscollection.systemmaintain.hafoperateLog.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface IHafoperatorLogBS {
  public List findHafOperateLog(Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException;
}
