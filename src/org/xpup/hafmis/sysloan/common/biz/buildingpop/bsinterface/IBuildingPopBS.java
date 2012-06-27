package org.xpup.hafmis.sysloan.common.biz.buildingpop.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface IBuildingPopBS {
  public List findBuildingList(Pagination pagination,SecurityInfo securityInfo)
    throws BusinessException;
}
