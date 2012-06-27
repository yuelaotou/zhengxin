package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.modifyaccount.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
public interface IModifyBS {
  public List getPrintInfo(Pagination pag,SecurityInfo a)throws BusinessException;
  public List getOpenMondifyInfoList(Pagination pag,SecurityInfo a) throws BusinessException;
  public List getOpenMondifyInfoList_YG(Pagination pag,SecurityInfo a) throws BusinessException;
}
