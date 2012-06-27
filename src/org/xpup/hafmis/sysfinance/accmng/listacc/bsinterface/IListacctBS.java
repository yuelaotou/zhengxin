package org.xpup.hafmis.sysfinance.accmng.listacc.bsinterface;

import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accmng.listacc.form.ListaccAF;

public interface IListacctBS {
  public List querylistaccmngList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  public String checkSubjectcode(String subjectcode, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  public String getMinYearmonth_WL(SecurityInfo securityInfo) throws Exception,
      BusinessException;

  public List queryDetailAccList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  public List queryExpDetailAccList(ListaccAF af,
      SecurityInfo securityInfo) throws Exception, BusinessException;
}
