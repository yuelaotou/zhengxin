package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 业务状态
 * @author 王玲
 *Jul 3, 2007
 */
public class OrgOverPay extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.ORGOVERPAYTYPE_2),
      new Integer(BusiConst.ORGOVERPAYTYPE_1)};

   static final String[] values = { "其他", "冲挂账" };
  public OrgOverPay()
  {
    this.putValues(keys,values);
  }
}
