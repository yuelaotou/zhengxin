package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 个贷-罚息利率类型
 * @author 王菱
 * 2007-9-13
 */
public class PLPunishInterestType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
                                  new Integer(BusiConst.PLPUNISHINTERESTTYPE_PUNISHDAYRATE),
                                  new Integer(BusiConst.PLPUNISHINTERESTTYPE_CONTRACTDAYRATE),
                                  new Integer(BusiConst.PLPUNISHINTERESTTYPE_PAYMENTDAYRATE)
                                };

  static final String[] values = { "按罚息日利率", "按合同日利率","按额每日" };

  public PLPunishInterestType() {
    this.putValues(keys, values);
  }
}
