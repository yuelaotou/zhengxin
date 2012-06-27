package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

/**
 * 借贷发生方向
 * @author 王菱
 *
 */
public class OccurredDirection extends AbsBusiProMap{
  private static final long serialVersionUID = 2003445450075369723L;
  static final Integer[] keys = {
      new Integer(BusiConst.OCCURREDDIRECTION_DEBIT),
      new Integer(BusiConst.OCCURREDDIRECTION_CREDIT),
      new Integer(BusiConst.OCCURREDDIRECTION_PARALLEL)
      };
  static final String[] values = { "借", "贷","平" };
  public OccurredDirection()
  {
    this.putValues(keys,values);
  }
}
