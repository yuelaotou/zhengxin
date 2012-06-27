package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

/**
 * 个贷-贷款类型
 * 
 * @author 王菱 2007-9-13
 */
public class PLLoanType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
                                  new Integer(BusiConst.PLLOANTYPE_FIVE),
                                  new Integer(BusiConst.PLLOANTYPE_FIVEUP),
                                  new Integer(BusiConst.PLLOANTYPE_ONEYEAR),
                                  new Integer(BusiConst.PLLOANTYPE_TWOYEAR)};

  static final String[] values = { "1-5年", "5年以上","一年期","两年期" };

  public PLLoanType() {
    this.putValues(keys, values);
  }
}
