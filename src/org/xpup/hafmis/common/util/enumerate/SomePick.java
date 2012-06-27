package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class SomePick extends AbsBusiProMap{
  private static final long serialVersionUID = 2003445450075369723L;
  static final Integer[] keys = {
//      new Integer(BusiConst.BUYHOUSE),
//      new Integer(BusiConst.GIVEMONEY),
//      new Integer(BusiConst.BUILD)
    new Integer(BusiConst.BUYHOUSE),
    new Integer(BusiConst.GIVEMONEYBYMON),
    new Integer(BusiConst.GIVEMONEYClEAR),
    new Integer(BusiConst.DISEASE),
    new Integer(BusiConst.DISTRESS),
    new Integer(BusiConst.PARTREST)
    
      };
  //static final String[] values = { "购房", "还贷","翻建" };
  static final String[] values = { "购房", "公积金按月还贷","公积金一次性还贷款","重大疾病","特困","部分提取其他" };
  public SomePick()
  {
    this.putValues(keys,values);
  }
}
