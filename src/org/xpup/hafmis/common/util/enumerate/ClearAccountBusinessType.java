package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 扎账业务类型
 * @author 王玲
 *2007-7-10
 */
public class ClearAccountBusinessType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = { 
      BusiConst.CLEARACCOUNTBUSINESSTYPE_A,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_B,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_M,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_C,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_D,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_E,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_F,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_G,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_K,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_L,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_H,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_I,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_J};

   static final String[] values = { "汇缴", "个人补缴","单位补缴","挂账","提取","转出", "转入","调其他","调缴存","调提取","结息","公积金余额结转","挂账余额结转" };
  public ClearAccountBusinessType()
  {
    this.putValues_Str(keys,values);
  }
}
