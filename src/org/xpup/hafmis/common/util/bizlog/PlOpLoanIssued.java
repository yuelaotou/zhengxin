package org.xpup.hafmis.common.util.bizlog;

/**
 * 操作模块-个贷-发放贷款
 * 
 * @author 王菱 2007-9-13
 */
public class PlOpLoanIssued extends BusiLogProMap {

  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.PL_OP_LOANISSUED_DO),
      new Integer(BusiLogConst.PL_OP_LOANISSUED_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_LOANISSUED_PRINTRESTORELOAN) };

  static final String[] values = { "发放贷款-办理发放", "发放贷款-发放维护", "发放贷款-打印还款计划表" };

  public PlOpLoanIssued() {
    this.putValues(keys, values);
  }

}
