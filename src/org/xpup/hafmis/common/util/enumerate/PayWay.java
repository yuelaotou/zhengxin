package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class PayWay  extends AbsBusiProMap {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  static final Integer[] keys = { 
    new Integer(BusiConst.PAY_WAY_HUAKUAN),
    new Integer(BusiConst.PAY_WAY_CHECK),
    new Integer(BusiConst.PAY_WAY_CASH)
    };

 static final String[] values = { "划款", "支票","现金" };
  public PayWay()
{
  this.putValues(keys,values);
}
}
