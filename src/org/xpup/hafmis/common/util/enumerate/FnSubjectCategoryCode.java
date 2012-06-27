package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * 财务-科目类别代码
 * @author 王菱
 * 2007-10-6
 */
public class FnSubjectCategoryCode extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.SUBCATEGORYCODE_ASSETS),
      new Integer(BusiConst.SUBCATEGORYCODE_LIABILITIES),
      new Integer(BusiConst.SUBCATEGORYCODE_INTERESTS),
      new Integer(BusiConst.SUBCATEGORYCODE_COST),
      new Integer(BusiConst.SUBCATEGORYCODE_PROFIT_LOSS)};

   static final String[] values = { "资产", "负债","权益","成本","损益" };
  public FnSubjectCategoryCode()
  {
    this.putValues(keys,values);
  }
}
