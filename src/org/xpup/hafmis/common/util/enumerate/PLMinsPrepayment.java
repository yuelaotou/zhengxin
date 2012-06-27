package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 个贷-提前还款最低金额
 * @author 王菱
 * 2007-9-14
 */
public class PLMinsPrepayment extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
                                  new Integer(BusiConst.PLMINSPREPAYMENT_1) 
                                };

  static final String[] values = { "提前还款最低金额" };

  public PLMinsPrepayment() {
    this.putValues(keys, values);
  }
}

