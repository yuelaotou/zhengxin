package org.xpup.hafmis.common.util.enumerate;
/**
 * 单位缴存方式
 * @author 王菱
 *2007-6-21
 */
import org.xpup.hafmis.common.util.BusiConst;

public class OrgPayWay extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { new Integer(BusiConst.ORGPAYWAY_RATE),
      new Integer(BusiConst.ORGPAYWAY_PAYMENT)};

   static final String[] values = { "单一缴率", "职工缴额" };
  public OrgPayWay()
  {
    this.putValues(keys,values);
  }
}
