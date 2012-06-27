package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class ClearInterestType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = {   
      BusiConst.CLEARACCOUNTBUSINESSTYPE_WL_A,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_WL_B,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_WL_C};

   static final String[] values = { "年终结息","销户提取","转移"};
  public ClearInterestType()
  {
    this.putValues_Str(keys,values);
  }
}
