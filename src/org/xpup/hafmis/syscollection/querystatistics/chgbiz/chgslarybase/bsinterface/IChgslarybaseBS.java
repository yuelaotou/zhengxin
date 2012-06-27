package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgslarybase.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgslarybase.from.ChgslarybaseListAF;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgslarybase.from.ChgslarybaseTbListAF;




public interface IChgslarybaseBS {
//根据条件查询ChgslarybaseListAF记录
  public ChgslarybaseListAF findChgslarybaseList(Pagination pagination) throws Exception,BusinessException;
//根据条件查询ChgslarybaseTbListAF记录
  public ChgslarybaseTbListAF findEmpChgslarybaseList(Pagination pagination) throws Exception,BusinessException;
}
