package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class CollLoanbackStatus extends AbsBusiProMap {
  /**
   * 郭婧平 公积金还贷状态
   */
  private static final long serialVersionUID = 1L;

  static final Integer[] keys = { new Integer(BusiConst.COLLLOANBACKSTATUS_IMPORT),
      new Integer(BusiConst.COLLLOANBACKSTATUS_KOUMONEY),
      new Integer(BusiConst.COLLLOANBACKSTATUS_EXPORT)};

  static final String[] values = { "导入", "扣款", "导出"};

  public CollLoanbackStatus() {
    this.putValues(keys, values);
  }
}
