package org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.bsinterface;

import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.common.domain.entity.ReportMng;
import org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.dto.ReportMngDTO;

public interface ICreateReportBS {
public List findcreateReportMngList(Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException;
public ReportMng queryCreateReportMngById(Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException;
public void insertCreateReportMng(ReportMngDTO reportMngDTO,SecurityInfo securityInfo) throws Exception, BusinessException;
public List findcreateReportMngAllList(Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException;
public String checkdeleteReportTable(String reporttableid,SecurityInfo securityInfo) throws Exception, BusinessException;
public void deleteReport(String reporttableid,SecurityInfo securityInfo) throws Exception, BusinessException;
public String checkModifyReport(String reporttableid,String newcol,String newrow,SecurityInfo securityInfo) throws Exception, BusinessException;
}