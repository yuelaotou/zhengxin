package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 特殊业务凭证模式
 * @author 王菱
 */
public class SpecailBizModel extends AbsBusiProMap{
  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = { 
      BusiConst.SPECAILBIZMODEL_OFFICE,
      BusiConst.SPECAILBIZMODEL_BANK,
      BusiConst.SPECAILBIZMODEL_ORG};

   static final String[] values = { "办事处", "银行","单位"};
  public SpecailBizModel()
  {
    this.putValues_Str(keys,values);
  }
}
