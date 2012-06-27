package org.xpup.hafmis.common.util.enumerate;
/**
 * 担保方式
 * @author 王菱
 *2007-6-22
 */
import org.xpup.hafmis.common.util.BusiConst;

public class GuaranteeType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.GUARANTEETYPE_IMPAWN),
      new Integer(BusiConst.GUARANTEETYPE_MORTAGAGE),
      new Integer(BusiConst.GUARANTEETYPE_NATURALPERSONHYPOTHECATE),
      new Integer(BusiConst.GUARANTEETYPE_CREDIT),
      new Integer(BusiConst.GUARANTEETYPE_COMBINATIONINNATURAL),
      new Integer(BusiConst.GUARANTEETYPE_COMBINATIONOUTNATURAL),
      new Integer(BusiConst.GUARANTEETYPE_JOINTGUARANTY),
      new Integer(BusiConst.GUARANTEETYPE_OTHERS)
      };

   static final String[] values = { "质押（含保证金）", "抵押","自然人担保","信用/免担保","组合（含自然人保证）","组合（不含自然人保证）","农户联保","其他" };
  public GuaranteeType()
  {
    this.putValues(keys,values);
  }
}
