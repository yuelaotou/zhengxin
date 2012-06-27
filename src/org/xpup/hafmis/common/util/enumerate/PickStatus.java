package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 提取状态
 * @author 王菱
 *
 */
public class PickStatus extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.PICKSTATUS_NO),
      new Integer(BusiConst.PICKSTATUS_YES)
      };

   static final String[] values = { "未使用", "已使用" };
  public PickStatus()
  {
    this.putValues(keys,values);
  }
}
