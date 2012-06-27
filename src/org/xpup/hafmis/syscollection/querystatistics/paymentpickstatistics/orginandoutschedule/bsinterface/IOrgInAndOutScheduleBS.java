package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orginandoutschedule.bsinterface;

import java.util.List;

import org.xpup.common.util.Pagination;

public interface IOrgInAndOutScheduleBS {
  public Object[] find(Pagination pagination);
  public List findPrintInfo(Pagination pagination);
}
