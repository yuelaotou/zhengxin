package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class PLCongealInfoThaw extends AbsBusiProMap {
  /**
   * 合同冻结表
   * 石岩
   */
  private static final long serialVersionUID = -2713181004952743102L;

  static final Integer[] keys = { new Integer(BusiConst.PLPREPAYMENTFEES_CONGEALINFOTHAW),
      new Integer(BusiConst.PLPREPAYMENTFEES_CONGEALINFOGELATION) };

  static final String[] values = { "解冻","冻结"};

  public PLCongealInfoThaw() {
    this.putValues(keys, values); 
  }

}