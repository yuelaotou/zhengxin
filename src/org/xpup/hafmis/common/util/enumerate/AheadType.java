package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class AheadType extends AbsBusiProMap {
  /**
   * 提前还款类型
   * 1.延长，2.缩短，3.不改变期限
   * jj
   */
  private static final long serialVersionUID = -2713181004952743102L;

  static final Integer[] keys = { new Integer(BusiConst.AHEADTYPE_1),
      new Integer(BusiConst.AHEADTYPE_2),
      new Integer(BusiConst.AHEADTYPE_3)};

  static final String[] values = { "延长", "缩短","不改变期限" };

  public AheadType() {
    this.putValues(keys, values); 
  }

}
