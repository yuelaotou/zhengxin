package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 财务-报表查询方式
 * @author 王菱
 * 2007-10-6
 */
public class FnReportQueryManner extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.REPORTQUERYMANNER_YEAR),
      new Integer(BusiConst.REPORTQUERYMANNER_MONTH),
      new Integer(BusiConst.REPORTQUERYMANNER_DAY)};

   static final String[] values = { "按年", "按月","按日" };
  public FnReportQueryManner()
  {
    this.putValues(keys,values);
  }
}

