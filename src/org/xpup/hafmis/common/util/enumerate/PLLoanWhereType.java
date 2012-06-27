package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 个贷-地点类型
 * @author 王硕
 * 20090409
 */
public class PLLoanWhereType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = { 
                                  BusiConst.PL_LOANTYPE_LOCAL,
                                  BusiConst.PL_LOANTYPE_OTHERS };

  static final String[] values = { "本地","异地" };

  public PLLoanWhereType() {
    this.putValues_Str(keys, values);
  }
}
