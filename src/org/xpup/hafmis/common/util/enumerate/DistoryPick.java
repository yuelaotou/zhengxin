package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class DistoryPick extends AbsBusiProMap{
  private static final long serialVersionUID = 2003445450075369723L;
  static final Integer[] keys = {
//      new Integer(BusiConst.DISTORY),
//      new Integer(BusiConst.END),
//      new Integer(BusiConst.USA)
    new Integer(BusiConst.BOWOUT),
    new Integer(BusiConst.DEATH),
    new Integer(BusiConst.DECRUITMENT),
    new Integer(BusiConst.JOBLESS),
    new Integer(BusiConst.DISTORY),
    new Integer(BusiConst.SETTLE)
   
      };
 // static final String[] values = { "解除劳动合同", "死亡","户籍迁出" };
  static final String[] values = { "退休", "死亡","调出市内","失业下岗两年","非本市户口解除合同","出国定居" };
  public DistoryPick()
  {
    this.putValues(keys,values);
  }
}
