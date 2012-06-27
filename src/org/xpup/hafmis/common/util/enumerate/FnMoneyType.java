package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

/**
 * 财务-金额类型
 * 
 * @author 王菱 2007-10-6
 */
public class FnMoneyType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = {
      new Integer(BusiConst.FNMONEYTYPE_COLL_PAYMENT),
      new Integer(BusiConst.FNMONEYTYPE_COLL_PICK),
      new Integer(BusiConst.FNMONEYTYPE_COLL_CLEARACCOUNTINTEREST),
      new Integer(BusiConst.FNMONEYTYPE_COLL_TRANSOUT),
      new Integer(BusiConst.FNMONEYTYPE_COLL_TTRANSIN),

      new Integer(BusiConst.FNMONEYTYPE_COLL_TRANSOUTINTEREST),
      new Integer(BusiConst.FNMONEYTYPE_COLL_CLEARINTEREST),
      new Integer(BusiConst.FNMONEYTYPE_COLL_OVERPAYMENT),
      new Integer(BusiConst.FNMONEYTYPE_LOAN_ACCORD),
      new Integer(BusiConst.FNMONEYTYPE_LOAN_RECEVERNORMALPRINCIPAL),

      new Integer(BusiConst.FNMONEYTYPE_LOAN_RECEVEROVERDUEPRINCIPAL),
      new Integer(BusiConst.FNMONEYTYPE_LOAN_RECEVERINTEREST),
      new Integer(BusiConst.FNMONEYTYPE_LOAN_CLEARRECOVERMONEY),
      new Integer(BusiConst.FNMONEYTYPE_LOAN_OVERPAYMENT),
      new Integer(BusiConst.FNMONEYTYPE_LOAN_MARGIN),

      new Integer(BusiConst.FNMONEYTYPE_LOAN_MARGININTEREST),
      new Integer(BusiConst.FNMONEYTYPE_LOAN_REALRECEVER),
      new Integer(BusiConst.FNMONEYTYPE_LOAN_PUNISHINTEREST),
      new Integer(BusiConst.FNMONEYTYPE_LOAN_PICKMONEY) };

  static final String[] values = { "公积金_缴存金额", "公积金_提取金额", "公积金_销户利息",
      "公积金_转出金额", "公积金_转入金额", "公积金_转出结息", "公积金_结息利息", "公积金_挂账金额", "贷款_发放金额",
      "贷款_回收正常本金", "贷款_回收逾期本金", "贷款_回收利息", "贷款_核销收回金额", "贷款_挂账金额", "贷款_保证金额",
      "贷款_保证金利息", "贷款_实收金额", "贷款_回收罚息", "提取金额" };

  public FnMoneyType() {
    this.putValues(keys, values);
  }
}
