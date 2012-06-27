package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class EspecialOperationType extends AbsBusiProMap{

  /**
   * 张列
   * 特殊业务类型
   */
  private static final long serialVersionUID = 1L;

  static final Integer[] keys = { new Integer(BusiConst.ESPECIALOPERATIONTYPE_AGENT),
    new Integer(BusiConst.ESPECIALOPERATIONTYPE_LOANBACK) };

static final String[] values = { "财政代扣", "公积金还贷" };

public EspecialOperationType() {
  this.putValues(keys, values);
}
}
