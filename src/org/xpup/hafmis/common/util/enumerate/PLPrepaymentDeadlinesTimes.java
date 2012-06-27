package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 贷款期限内最多允许提前还款
 * @author 王菱
 * 2007-9-14
 */
public class PLPrepaymentDeadlinesTimes extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
                                  new Integer(BusiConst.PLPREPAYMENTDEADLINESTIMES_1) 
                                };

  static final String[] values = { "贷款期限内最多允许提前还款" };

  public PLPrepaymentDeadlinesTimes() {
    this.putValues(keys, values);
  }
}



