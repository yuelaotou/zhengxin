package org.xpup.hafmis.sysloan.common.biz.assistantorgpop.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

/**
 * 
 * @author yuqf
 *
 */
public interface IAssistantorgpopBS {
  public List findAssistantpopList(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
}
