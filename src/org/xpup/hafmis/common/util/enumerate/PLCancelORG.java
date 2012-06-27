package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 个贷-核销单位
 * @author 王菱
 * 2007-9-14
 */
public class PLCancelORG extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
                                  new Integer(BusiConst.PLCANCELORG_CENTRE),
                                  new Integer(BusiConst.PLCANCELORG_GUARANTEECORP),
                                  new Integer(BusiConst.PLCANCELORG_OTHERS)
                                };

  static final String[] values = { "中心","担保公司","其他"
                                 };

  public PLCancelORG() {
    this.putValues(keys, values);
  }
}


