package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class AddPayType extends AbsBusiProMap {
  /**
   * ²¹½É×´Ì¬ 
   * 1Õý³£²¹½É 2²î¶î²¹½É 3²»¶¨ÆÚ²¹½É 
   * yqf
   */
  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = {
      BusiConst.ADDPAYTYPE_A,
      BusiConst.ADDPAYTYPE_B,
      BusiConst.ADDPAYTYPE_C
     };

  static final String[] values = { "Õý³£²¹½É", "²î¶î²¹½É", "²»¶¨ÆÚ²¹½É" };

  public AddPayType() {
    this.putValues_Str(keys, values);
  }
}
