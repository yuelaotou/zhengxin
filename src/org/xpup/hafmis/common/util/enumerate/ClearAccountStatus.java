package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class ClearAccountStatus extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
     new Integer(BusiConst.CLEARACCOUNTSTATUS_3),
     new Integer(BusiConst.CLEARACCOUNTSTATUS_4),
     new Integer(BusiConst.CLEARACCOUNTSTATUS_5)
     };

   static final String[] values = { "»∑»œ","∏¥∫À","»Î’À" };
  public ClearAccountStatus()
  {
    this.putValues(keys,values);
  }
}
