package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 单位版业务类型
 * @author jj
 */
public class OrgBusinessType extends AbsBusiProMap {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  static final String[] keys = { 
      BusiConst.ORGBUSINESSTYPE_A,
      BusiConst.ORGBUSINESSTYPE_B,
      BusiConst.ORGBUSINESSTYPE_C,
      BusiConst.ORGBUSINESSTYPE_D,
      BusiConst.ORGBUSINESSTYPE_E,
      BusiConst.ORGBUSINESSTYPE_F,
      BusiConst.ORGBUSINESSTYPE_G,
      BusiConst.ORGBUSINESSTYPE_H,
      BusiConst.ORGBUSINESSTYPE_I,
      BusiConst.ORGBUSINESSTYPE_J,
      BusiConst.ORGBUSINESSTYPE_K,
      BusiConst.ORGBUSINESSTYPE_L,
      BusiConst.ORGBUSINESSTYPE_M,
      BusiConst.ORGBUSINESSTYPE_N,
      BusiConst.ORGBUSINESSTYPE_O,
      BusiConst.ORGBUSINESSTYPE_P,
      BusiConst.ORGBUSINESSTYPE_Q};

   static final String[] values = { "汇缴", "单位补缴","挂账","提取","转出","转入", "调账","结息","特殊提取","利率调整","个人补缴","汇缴比例调整","工资基数调整","缴额调整","人员变更","单位开户","单位变更" };
  public OrgBusinessType()
  {
    this.putValues_Str(keys,values);
  }
}
