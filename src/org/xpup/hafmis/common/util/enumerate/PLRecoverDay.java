package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 个贷-还款日
 * @author 王菱
 * 2007-9-13
 */
public class PLRecoverDay extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
                                  new Integer(BusiConst.PLRECOVERDAY_ACCOUNT),
                                  new Integer(BusiConst.PLRECOVERDAY_DAY) };

  static final String[] values = { "按户定日", "统一定日" };

  public PLRecoverDay() {
    this.putValues(keys, values);
  }
}
