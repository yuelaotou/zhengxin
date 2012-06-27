package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.empolderquery.bsinterface;

import java.util.List;

import org.xpup.common.util.Pagination;

public interface IEmpolderQueryBS {
  /**
   * 查询开发商列表的方法
   * @param pagination 查询条件
   * @return
   * @throws Exception
   */
  public List findDevelopList(Pagination pagination) throws Exception ;
}
