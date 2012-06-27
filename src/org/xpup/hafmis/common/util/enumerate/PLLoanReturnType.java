package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 个贷-贷款还款类型
 * @author 刘洋
 * 2007-9-13
 */
public class PLLoanReturnType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = {   
    new Integer(BusiConst.PLLOANRETURNTYPE_CENTER),
    new Integer(BusiConst.PLLOANRETURNTYPE_BANK)
  };

 static final String[] values = { "以中心为主","以银行为主" };
public PLLoanReturnType()
{
  this.putValues(keys,values);
}
}