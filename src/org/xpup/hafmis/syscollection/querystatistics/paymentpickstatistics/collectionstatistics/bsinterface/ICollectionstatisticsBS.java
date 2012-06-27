package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.collectionstatistics.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.collectionstatistics.form.CollectionstatisticsAF;

public interface ICollectionstatisticsBS {
  public CollectionstatisticsAF findChgorgrateByCriterions(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
}
