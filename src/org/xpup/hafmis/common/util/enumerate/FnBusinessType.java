package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

/**
 * 财务-业务类型
 * 
 * @author 王菱 2007-10-6
 */
public class FnBusinessType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = {
      new Integer(BusiConst.FNBUSINESSTYPE_COLL_PAYMENT),
      new Integer(BusiConst.FNBUSINESSTYPE_COLL_SOMEPICK),
      new Integer(BusiConst.FNBUSINESSTYPE_COLL_ALLPICK),
      new Integer(BusiConst.FNBUSINESSTYPE_COLL_OUTTRANSOUT),
      new Integer(BusiConst.FNBUSINESSTYPE_COLL_OUTTRANSIN),

      new Integer(BusiConst.FNBUSINESSTYPE_COLL_INTRANSOUT),
      new Integer(BusiConst.FNBUSINESSTYPE_COLL_INTRANSIN),
      new Integer(BusiConst.FNBUSINESSTYPE_COLL_INTRANS),
      new Integer(BusiConst.FNBUSINESSTYPE_COLL_OVERPAYMENT),
      new Integer(BusiConst.FNBUSINESSTYPE_COLL_OVERPICKPAYMENT),

      new Integer(BusiConst.FNBUSINESSTYPE_COLL_CLEARINTEREST),
      new Integer(BusiConst.FNBUSINESSTYPE_COLL_ERRACCOUNTUP),
      new Integer(BusiConst.FNBUSINESSTYPE_COLL_ERRACCOUNTDOWN),
      new Integer(BusiConst.FNBUSINESSTYPE_COLL_GJJBACK),
      new Integer(BusiConst.FNBUSINESSTYPE_LOAN_ACCORD),
      new Integer(BusiConst.FNBUSINESSTYPE_LOAN_CALLBACK), };

  static final String[] values = { "公积金_缴存", "提取还贷", "公积金_提取", "公积金_外部转出", "公积金_外部转入",
      "公积金_内部转出", "公积金_内部转入", "公积金_内部转入转出", "公积金_挂账（收）", "公积金_挂账（提取）",
      "公积金_结息", "公积金_错账调增", "公积金_错账调减", "公积金_还贷", "贷款_发放", "贷款_回收" };

  public FnBusinessType() {
    this.putValues(keys, values);
  }
}
