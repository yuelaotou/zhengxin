package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 业务类型
 * @author 王玲
 *2007-7-4
 */
public class BusinessType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.BUSINESSTYPE_1),
      new Integer(BusiConst.BUSINESSTYPE_2),
      new Integer(BusiConst.BUSINESSTYPE_3),
      new Integer(BusiConst.BUSINESSTYPE_4)};

   static final String[] values = { "正常汇缴", "单位补缴","个人补缴","挂账"  };
  public BusinessType()
  {
    this.putValues(keys,values);
  }
}
