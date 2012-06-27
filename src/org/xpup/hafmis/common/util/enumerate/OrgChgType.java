package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 单位变更变更状态
 * @author 王玲
 *2007-7-7
 */
public class OrgChgType  extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = {
      BusiConst.ORGCHGTYPE_1,
      BusiConst.ORGCHGTYPE_2,
      BusiConst.ORGCHGTYPE_3};

   static final String[] values = { "正常","封存", "注销" };
  public OrgChgType()
  {
    this.putValues_Str(keys,values);
  }
}
