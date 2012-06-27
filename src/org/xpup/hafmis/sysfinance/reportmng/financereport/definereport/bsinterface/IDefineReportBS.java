package org.xpup.hafmis.sysfinance.reportmng.financereport.definereport.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.reportmng.financereport.definereport.form.DefineReportAF;

public interface IDefineReportBS {
public DefineReportAF queryReportMessage(DefineReportAF defineReportAF,Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException;
public void saveReportMessage(DefineReportAF defineReportAF,Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException;
}