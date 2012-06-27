package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class AppStatus extends AbsBusiProMap {
  /**
   * 变更状态
   * 先前的变更状态1，2不能满足贷款的业务要求，所以添加此枚举项。状态是0，1。
   * 石岩
   */
  private static final long serialVersionUID = -2713181004952743102L;

  static final Integer[] keys = { new Integer(BusiConst.APPSTATUS_1),
      new Integer(BusiConst.APPSTATUS_2) };

  static final String[] values = { "未启用", "启用" };

  public AppStatus() {
    this.putValues(keys, values); 
  }

}
