package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 个贷-常用状态-允许or不允许
 * @author 王菱
 * 2007-9-14
 */
public class PLCommonStatus_SEC extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
                                  new Integer(BusiConst.PLCOMMONSTATUS_2_ALLOW),
                                  new Integer(BusiConst.PLCOMMONSTATUS_2_NOTALLOW) };

  static final String[] values = { "允许", "不允许" };

  public PLCommonStatus_SEC() {
    this.putValues(keys, values);
  }
}
