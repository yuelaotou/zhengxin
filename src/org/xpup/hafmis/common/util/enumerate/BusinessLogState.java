package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 业务状态
 * @author 王玲
 *Jul 3, 2007
 */
public class BusinessLogState extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = { 
      BusiConst.BUSINESSLOGSTATE_1,
      BusiConst.BUSINESSLOGSTATE_2,
      BusiConst.BUSINESSLOGSTATE_3,
      BusiConst.BUSINESSLOGSTATE_4,
      BusiConst.BUSINESSLOGSTATE_5};

   static final String[] values = { "录入清册", "登记","确认","复核","入账" };
  public BusinessLogState()
  {
    this.putValues_Str(keys, values);
  }
}
