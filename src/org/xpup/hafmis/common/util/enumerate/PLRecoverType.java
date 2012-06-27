package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 个贷-还款方式
 * @author 王菱
 * 2007-9-13
 */
public class PLRecoverType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
                                  new Integer(BusiConst.PLRECOVERTYPE_CORPUS),
                                  new Integer(BusiConst.PLRECOVERTYPE_INTEREST),
                                  new Integer(BusiConst.PLRECOVERTYPE_OTHER),
                                  new Integer(BusiConst.PLRECOVERTYPE_ONEYEAR),
                                  new Integer(BusiConst.PLRECOVERTYPE_TWOYEAR)
                                  };

  static final String[] values = { "等额本金","等额本息","其它","一年期","两年期"};

  public PLRecoverType() {
    this.putValues(keys, values);
  }
}
