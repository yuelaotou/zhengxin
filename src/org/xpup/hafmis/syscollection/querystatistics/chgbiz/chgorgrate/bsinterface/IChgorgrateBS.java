package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgorgrate.bsinterface;

import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgorgrate.form.ChgorgrateAF;

public interface IChgorgrateBS {

  public ChgorgrateAF findChgorgrateByCriterions(Pagination pagination,SecurityInfo securityInfo) throws Exception;
  
}
