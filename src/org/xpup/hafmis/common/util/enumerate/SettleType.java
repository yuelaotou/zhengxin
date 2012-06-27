package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class SettleType extends AbsBusiProMap {

  /**
   * 张列 核算类型
   */
  private static final long serialVersionUID = 1L;

  static final Integer[] keys = { new Integer(BusiConst.SETTLETYPE_ALL),
      new Integer(BusiConst.SETTLETYPE_ORG),
      new Integer(BusiConst.SETTLETYPE_BANK),
      new Integer(BusiConst.SETTLETYPE_OFFICE) };

  static final String[] values = { "全部", "单位", "银行", "办事处" };

  public SettleType() {
    this.putValues(keys, values);
  }
}
