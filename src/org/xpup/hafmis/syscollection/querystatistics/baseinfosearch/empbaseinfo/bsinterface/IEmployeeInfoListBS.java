package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseinfo.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseinfo.form.EmployeeInfoSearchAF;

public interface IEmployeeInfoListBS {
  public Emp findEmpByOrdIdAndEmpId(Integer orgId, Integer empId)
      throws BusinessException;

  public EmployeeInfoSearchAF getEmployeeInfoList(Pagination pag,
      SecurityInfo info) throws BusinessException;

  public List getEmployeeInfoAllList(Pagination pag, SecurityInfo info)
      throws BusinessException;
}
