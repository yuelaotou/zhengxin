package org.xpup.hafmis.common.util.bizlog;
/**
 * 操作模块-财务-报表管理
 * @author 王菱
 * 2007-10-8
 */
public class FnOpReportmng extends BusiLogProMap {

  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.FN_OP_REPORTMNG_FINANCEREPORT_CREATEREPORT)
      };

  static final String[] values = { "报表管理-财务报表-创建报表"
                                 };

  public FnOpReportmng() {
    this.putValues(keys, values);
  }

}
