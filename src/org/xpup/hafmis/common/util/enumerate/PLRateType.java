package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 个贷-利率类型
 * @author 王菱
 * 2007-9-13
 */
public class PLRateType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
                                  new Integer(BusiConst.PLRATETYPE_SUDTDEMAND),
                                  new Integer(BusiConst.PLRATETYPE_SUBTREGULAR) };

  static final String[] values = { "活期利率","定期利率" };

  public PLRateType() {
    this.putValues(keys, values);
  }
}
