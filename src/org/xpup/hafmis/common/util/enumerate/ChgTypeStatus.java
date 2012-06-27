package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 变更状态
 * @author 王菱
 * 2007-7-8
 */
public class ChgTypeStatus extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.CHGTYPESTATUS_1),
      new Integer(BusiConst.CHGTYPESTATUS_2)};

   static final String[] values = { "未启用", "启用"};
  public ChgTypeStatus()
  {
    this.putValues(keys,values);
  }
}
