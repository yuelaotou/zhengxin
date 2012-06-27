package org.xpup.hafmis.sysloan.querystatistics.querystatistics.querycongeallog.bsinterface;

import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface IQueryCongeallogBS {

  //获得冻结表信息
  public List queryCongeallogList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception;

  //获得冻结表信息记录数
  public int queryCongeallogListCount(Pagination pagination,
      SecurityInfo securityInfo) throws Exception;
  
  public List queryCongeallogAllList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception;
}
