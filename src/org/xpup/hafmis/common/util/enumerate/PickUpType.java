package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 提取类型
 * @author 王玲
 *Jul 3, 2007
 */
public class PickUpType extends AbsBusiProMap{

  private static final long serialVersionUID = 2003445450075369723L;

    static final Integer[] keys = {
        new Integer(BusiConst.PICKUPTYPE_1),
        new Integer(BusiConst.PICKUPTYPE_2)
        };

    static final String[] values = { "部分提取", "销户提取"};
    public PickUpType()
    {
      this.putValues(keys,values);
    }
  }


