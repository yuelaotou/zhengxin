package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 个贷-业务状态
 * @author 王菱
 * 2007-9-14
 */
public class PLBusinessState extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
                                  new Integer(BusiConst.PLBUSINESSSTATE_EXP),
                                  new Integer(BusiConst.PLBUSINESSSTATE_IMP),
                                  new Integer(BusiConst.PLBUSINESSSTATE_SIGN),
                                  new Integer(BusiConst.BUSINESSSTATE_SURE),
                                  new Integer(BusiConst.BUSINESSSTATE_CHECK),
                                  new Integer(BusiConst.BUSINESSSTATE_CLEARACCOUNT)
                                };

  static final String[] values = { "导出","导入","登记","确认","复核","入账"
                                 };

  public PLBusinessState() {
    this.putValues(keys, values);
  }
}


