package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class TenNumber extends AbsBusiProMap{
  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.TENNUMBER_1),
      new Integer(BusiConst.TENNUMBER_2),
      new Integer(BusiConst.TENNUMBER_3),
      new Integer(BusiConst.TENNUMBER_4),
      new Integer(BusiConst.TENNUMBER_5),
      new Integer(BusiConst.TENNUMBER_6),
      new Integer(BusiConst.TENNUMBER_7),
      new Integer(BusiConst.TENNUMBER_8),
      new Integer(BusiConst.TENNUMBER_9),
      new Integer(BusiConst.TENNUMBER_10),
      new Integer(BusiConst.TENNUMBER_11),
      new Integer(BusiConst.TENNUMBER_12),
      new Integer(BusiConst.TENNUMBER_13),
      new Integer(BusiConst.TENNUMBER_14),
      new Integer(BusiConst.TENNUMBER_15),
      new Integer(BusiConst.TENNUMBER_16),
      new Integer(BusiConst.TENNUMBER_17),
      new Integer(BusiConst.TENNUMBER_18),
      new Integer(BusiConst.TENNUMBER_19),
      new Integer(BusiConst.TENNUMBER_20),
      new Integer(BusiConst.TENNUMBER_21),
      new Integer(BusiConst.TENNUMBER_22)};

   static final String[] values = { "One", "Two","Three","Four","Five","Six","Seven",
     "Eight","Nine","Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen","Twenty","Twentyone","Twentytwo" };
  public TenNumber()
  {
    this.putValues(keys,values);
  }
}
