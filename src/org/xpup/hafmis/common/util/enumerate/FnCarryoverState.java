package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class FnCarryoverState extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.CARRYOVERSTATE_NO),
      new Integer(BusiConst.CARRYOVERSTATE_YESNOKEEPING),
      new Integer(BusiConst.CARRYOVERSTATE_BOOKKEEPING)};

   static final String[] values = { "未结转", "已结转未记账","已记账" };
  public FnCarryoverState()
  {
    this.putValues(keys,values);
  }
}

