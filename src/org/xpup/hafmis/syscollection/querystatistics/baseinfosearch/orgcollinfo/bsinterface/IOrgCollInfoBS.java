package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.bsinterface;

import org.xpup.common.util.Pagination;

public interface IOrgCollInfoBS {
  public Object[] findOrgCollInfo(Pagination pagination) throws Exception;
}
