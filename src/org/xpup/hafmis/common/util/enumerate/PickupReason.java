package org.xpup.hafmis.common.util.enumerate;
import org.xpup.hafmis.common.util.BusiConst;
public class PickupReason extends AbsBusiProMap{
  private static final long serialVersionUID = 2003445450075369723L;
  static final Integer[] keys = {
    
    new Integer(BusiConst.PICKUPREASON_BUYHOUSE),
    new Integer(BusiConst.PICKUPREASON_GIVEMONEYBYMON),
    new Integer(BusiConst.PICKUPREASON_GIVEMONEYClEAR),
    new Integer(BusiConst.PICKUPREASON_DISEASE),
    new Integer(BusiConst.PICKUPREASON_DISTRESS),
    new Integer(BusiConst.PICKUPREASON_PARTREST),
    new Integer(BusiConst.PICKUPREASON_BOWOUT),
    new Integer(BusiConst.PICKUPREASON_DEATH),
    new Integer(BusiConst.PICKUPREASON_DECRUITMENT),
    new Integer(BusiConst.PICKUPREASON_JOBLESS),
    new Integer(BusiConst.PICKUPREASON_DISTORY),
    new Integer(BusiConst.PICKUPREASON_SETTLE)
      };
 // static final String[] values = { "解除劳动合同", "死亡","户籍迁出" };
  static final String[] values = { "购房", "公积金按月还贷","公积金一次性还贷款","重大疾病","特困","部分提取其他","退休", "死亡","调出市内","失业下岗两年","非本市户口解除合同","出国定居"};
  public PickupReason()
  {
    this.putValues(keys,values);
  }
}
