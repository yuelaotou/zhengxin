package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 个贷-提前还款后对还贷参数的更改类型
 * @author 王菱
 * 2007-9-14
 */
public class PLRecoverParasChg extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
                                  new Integer(BusiConst.PLRECOVERPARASCHG_SAMEPAY),
                                  new Integer(BusiConst.PLRECOVERPARASCHG_SAMEMONTHS),
                                  new Integer(BusiConst.PLRECOVERPARASCHG_CHGMONTHS)
                                };

  static final String[] values = { "保持原来月还款额","保持剩余期限","允许改变剩余期限"
                                 };

  public PLRecoverParasChg() {
    this.putValues(keys, values);
  }
}


