package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseexport.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;

public interface IEmpBaseExportsBS {
  public List getEmployeeInfoAllList(String orgid, List returnList)
      throws BusinessException;

  public Org getOrg(String orgid) throws BusinessException;
}
