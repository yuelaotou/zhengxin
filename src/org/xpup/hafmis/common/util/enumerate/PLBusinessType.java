package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

/**
 * 个贷-业务类型
 * 
 * @author 王菱 2007-9-14
 */
public class PLBusinessType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { new Integer(BusiConst.PLBUSINESSTYPE_ISSUED),
      new Integer(BusiConst.PLBUSINESSTYPE_SINGLERECOVER),
      new Integer(BusiConst.PLBUSINESSTYPE_PARTRECOVER),
      new Integer(BusiConst.PLBUSINESSTYPE_ALLCLEAR),
      new Integer(BusiConst.PLBUSINESSTYPE_SOMERECOVER),
      new Integer(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFCENTRE),
      new Integer(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFOTHER),
      new Integer(BusiConst.PLBUSINESSTYPE_BADDEBTSRECOVERCENTER),
      new Integer(BusiConst.PLBUSINESSTYPE_BADDEBTSRECOVEROTHER),
      new Integer(BusiConst.PLBUSINESSTYPE_CARRYOVERDUE),
      new Integer(BusiConst.PLBUSINESSTYPE_CARRYPAY),
      new Integer(BusiConst.PLBUSINESSTYPE_MISDIRECTCHG),
      new Integer(BusiConst.PLBUSINESSTYPE_OVERPAY),
      new Integer(BusiConst.PLBUSINESSTYPE_MARGIN),
      new Integer(BusiConst.PLBUSINESSTYPE_CLEARINTEREST),
      new Integer(BusiConst.PLBUSINESSTYPE_INITDATA)
  };

  static final String[] values = { "发放", "单笔回收", "部分提前还款", "一次性还清", "批量回收",
      "呆账核销（中心）", "呆账核销（其他）", "核销收回（中心）", "核销收回（其他）", "结转逾期", "结转余额", "错账调整",
      "挂账", "保证金", "结息", "初始化数据"};

  public PLBusinessType() {
    this.putValues(keys, values);
  }
}
