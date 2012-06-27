package org.xpup.hafmis.common.util.enumerate;
/**
 * 缴存精度
 * @author 王菱
 *2007-6-21
 */
import org.xpup.hafmis.common.util.BusiConst;


public class PaymentAccuracy extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.PAYMENTACCURACY_ROUNDTOYUAN),
      new Integer(BusiConst.PAYMENTACCURACY_DISCARDTOYUAN),
      new Integer(BusiConst.PAYMENTACCURACY_SEEKOKONYUAN),
      new Integer(BusiConst.PAYMENTACCURACY_SEECENTSONKOK),
      new Integer(BusiConst.PAYMENTACCURACY_ROUNDTOKOK),
      new Integer(BusiConst.PAYMENTACCURACY_DISCARDTOKOK),
      new Integer(BusiConst.PAYMENTACCURACY_SEEKOKCENTSONYUAN),
      new Integer(BusiConst.PAYMENTACCURACY_ROUNDTOCENT)};

   static final String[] values = { "四舍五入到元", "舍元以下","见角进元","见分进角","四舍五入到角","舍角以下","见角分进元","四舍五入到分" };
  public PaymentAccuracy()
  {
    this.putValues(keys,values);
  }
}
