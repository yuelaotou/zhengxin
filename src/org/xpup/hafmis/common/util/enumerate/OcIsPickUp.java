package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class OcIsPickUp extends AbsBusiProMap{
  /**
   * 
   */
  private static final long serialVersionUID = -6412611873040102553L;
  static final Integer[] keys = {
    new Integer(BusiConst.OC_NOT_PICK_UP),
    new Integer(BusiConst.OC_PICK_UP),
    new Integer(BusiConst.OC_PICK_UP_OVER)
    };
static final String[] values = { "未提取", "已提取","未提交" };
public OcIsPickUp()
{
  this.putValues(keys,values);
}
}
