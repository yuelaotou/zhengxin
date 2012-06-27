package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

/**
 * 所在地区
 * 
 * @author 王玲 2007-6-29
 */
public class InArea extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = {

  new Integer(BusiConst.INAREA_SZ), new Integer(BusiConst.INAREA_DSQ),
      new Integer(BusiConst.INAREA_GX), new Integer(BusiConst.INAREA_BYQ) };

  static final String[] values = { "市直", "大石桥", "盖县", "鲅鱼圈" };

  public InArea() {
    this.putValues(keys, values);
  }
}
