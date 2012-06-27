package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 单位变更变更类型
 * @author 王菱
 *
 */
public class OrgChgStruts extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = { 
      BusiConst.ORGCHGSTRUTS_1,
      BusiConst.ORGCHGSTRUTS_2,
      BusiConst.ORGCHGSTRUTS_3,
      BusiConst.ORGCHGSTRUTS_4};

   static final String[] values = { "开户","启封","封存", "注销" };
  public OrgChgStruts()
  {
    this.putValues_Str(keys,values);
  }
}
