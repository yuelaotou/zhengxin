package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 财务-余额方向
 * @author 王菱
 * 2007-10-6
 */
public class FnBalanceDirection extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = { 
      BusiConst.BALANCEDIRECTION_DEBIT,
      BusiConst.BALANCEDIRECTION_CREDIT,
      BusiConst.BALANCEDIRECTION_AVE};

   static final String[] values = { "借", "贷","平" };
  public FnBalanceDirection()
  {
    this.putValues_Str(keys,values);
  }
}
