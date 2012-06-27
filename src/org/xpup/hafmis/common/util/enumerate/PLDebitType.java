package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 个贷-扣款方式
 * @author 王菱
 * 2007-9-13
 */
public class PLDebitType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
                                  new Integer(BusiConst.PLDEBITTYPE_SUFF),
                                  new Integer(BusiConst.PLDEBITTYPE_ALL) };

  static final String[] values = { "足额扣款", "全额扣款" };

  public PLDebitType() {
    this.putValues(keys, values);
  }
}
