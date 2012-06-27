package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 财务-凭证类型
 * @author 王菱
 * 2007-10-6
 */
public class FnCredenceType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.CREDENCETYPE_CASH),
      new Integer(BusiConst.CREDENCETYPE_BANK)};

   static final String[] values = { "现金凭证", "银行存款凭证" };
  public FnCredenceType()
  {
    this.putValues(keys,values);
  }
}
