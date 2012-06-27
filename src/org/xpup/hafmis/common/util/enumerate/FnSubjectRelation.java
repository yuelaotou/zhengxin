package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 财务-核算关系类型
 * @author 王菱
 * 2007-10-6
 */
public class FnSubjectRelation extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.SUBRELATION_OFFICE),
      new Integer(BusiConst.SUBRELATION_BANK),
      new Integer(BusiConst.SUBRELATION_ORG),
      new Integer(BusiConst.SUBRELATION_OTHERS)};

   static final String[] values = { "办事处", "银行","单位","其他" };
  public FnSubjectRelation()
  {
    this.putValues(keys,values);
  }
}
