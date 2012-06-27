package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class FnSettleType extends AbsBusiProMap{

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { new Integer(BusiConst.FNSEETLETYPE_UNIONIZE),
      new Integer(BusiConst.FNSEETLETYPE_INDEPENDENCE)};

  static final String[] values = { "Õ≥“ª∫ÀÀ„", "∂¿¡¢∫ÀÀ„" };

  public FnSettleType()
  {
    this.putValues(keys, values);
  }
}
