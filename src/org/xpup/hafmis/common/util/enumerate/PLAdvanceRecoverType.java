package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 个贷-提前还款类型
 * @author 王菱
 * 2007-9-13
 */
public class PLAdvanceRecoverType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
                                  new Integer(BusiConst.PLADVANCERECOVERTYPE_PART),
                                  new Integer(BusiConst.PLADVANCERECOVERTYPE_ALL)
                                };

  static final String[] values = { "部分提前还款","一次性清还"
                                 };

  public PLAdvanceRecoverType() {
    this.putValues(keys, values);
  }
}

