package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class PLCongealInfoType extends AbsBusiProMap {
  /**
   * 
   */
  private static final long serialVersionUID = -2637814656795169943L;

  /**
   * 合同变更信息
   * 修改合同类型1.借款人信息，2.辅助借款人信息3.购房信息4.抵押合同信息，5.质押合同信息6.保证人
   * 石岩
   */
 

  static final Integer[] keys = { 
      new Integer(BusiConst.PLPREPAYMENTFEES_BORROWERTYPE),
      new Integer(BusiConst.PLPREPAYMENTFEES_AUXILIARYTYPE),
      new Integer(BusiConst.PLPREPAYMENTFEES_BIALTYPE) };

  static final String[] values = { "借款人", "辅助借款人","保证人" };

  public PLCongealInfoType() {
    this.putValues(keys, values);
  }

}
