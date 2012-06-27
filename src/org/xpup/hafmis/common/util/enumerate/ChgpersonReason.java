package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 人员变更的变更原因
 * @author 王菱
 *
 */
public class ChgpersonReason extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.CHGPERSONREASON_OTHER),
      new Integer(BusiConst.CHGPERSONREASON_OUT),
      new Integer(BusiConst.CHGPERSONREASON_IN),
      new Integer(BusiConst.CHGPERSONREASON_DEL)};

   static final String[] values = { "其他", "转出","转入","销户"};
  public ChgpersonReason()
  {
    this.putValues(keys,values);
  }
}
