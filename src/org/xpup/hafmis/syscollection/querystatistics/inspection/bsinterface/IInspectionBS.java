package org.xpup.hafmis.syscollection.querystatistics.inspection.bsinterface;

import java.math.BigDecimal;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.inspection.form.InspectionShowAF;

public interface IInspectionBS {
  public InspectionShowAF querygjjpayrate(Pagination pagination)throws Exception;
}
