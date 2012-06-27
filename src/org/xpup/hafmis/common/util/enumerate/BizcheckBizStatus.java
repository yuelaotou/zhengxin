package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

/**
 * ÒµÎñ¸´ºË ÒµÎñ×´Ì¬
 * @author ÓÚÇì·á
 *2007-07-11
 */
public class BizcheckBizStatus extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { new Integer(BusiConst.BIZCHECKBIZSTATUS_1),
      new Integer(BusiConst.BIZCHECKBIZSTATUS_2)};

  static final String[] values = { "Î´¸´ºË", "ÒÑ¸´ºË" };

  public BizcheckBizStatus()
  {
    this.putValues(keys, values);
  }
}
