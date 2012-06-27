package org.xpup.hafmis.sysfinance.reportmng.financereport.queryreport.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.reportmng.financereport.queryreport.form.QueryReportAF;

public interface IQueryReportBS {
public QueryReportAF queryReportMessage(QueryReportAF queryReportAF,Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException;
}