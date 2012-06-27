package org.xpup.hafmis.sysloan.querystatistics.querystatistics.yearloancontrast.bsinterface;

import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.yearloancontrast.form.YearLoanContrastAF;

public interface IYearLoanContrastBS {
  public YearLoanContrastAF queryList( Pagination pagination, SecurityInfo securityInfo)throws Exception ;
}
