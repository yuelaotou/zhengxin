package org.xpup.hafmis.sysfinance.accmng.totleacc.bsinterface;

import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface ITotleaccBS {
public String querySubjectdayreportParamValue(String bookId) throws Exception; 
public List querylistaccmngList(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
public String checkSubjectcode(String subjectcode,SecurityInfo securityInfo) throws Exception,BusinessException;
public String getMinYearmonth_WL(SecurityInfo securityInfo) throws Exception, BusinessException ;
}
