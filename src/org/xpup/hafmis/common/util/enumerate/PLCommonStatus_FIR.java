package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 个贷-常用状态-正常or作废
 * @author 王菱
 * 2007-9-13
 */
public class PLCommonStatus_FIR extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
                                  new Integer(BusiConst.PLCOMMONSTATUS_1_NORMAL),
                                  new Integer(BusiConst.PLCOMMONSTATUS_1_CANCELED)
                                //  new Integer(BusiConst.PLCOMMONSTATUS_1_RELIEVE)
                                  };

  static final String[] values = { "正常", "作废" };//,"解除"

  public PLCommonStatus_FIR() {
    this.putValues(keys, values);
  }
}
