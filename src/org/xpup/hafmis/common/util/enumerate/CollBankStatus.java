package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 归集银行状态
 * @author 王玲
 *2007-7-23
 */
public class CollBankStatus extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { new Integer(BusiConst.COLLBANKSTATUS_NORMAL),
      new Integer(BusiConst.COLLBANKSTATUS_CANCELED)};

  static final String[] values = { "正常", "作废" };

  public CollBankStatus()
  {
    this.putValues(keys, values);
  }
}
