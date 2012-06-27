package org.xpup.hafmis.common.util.bizlog;

/**
 * 操作模块-个贷-回收贷款
 * 
 * @author 王菱 2007-9-13
 */
public class PlOpLoanRecover extends BusiLogProMap {

  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_RECOVERQUIRY),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_DO),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_LOANKOUEXP),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_LOANKOUIMP),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_BADDEBTSOFF_DO),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_BADDEBTSOFF_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_CANRECOVER_DO),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_CANRECOVER_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_LIVING_DO),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_LIVING_MAINTAIN)
      };

  static final String[] values = { "回收贷款-回收咨询", "回收贷款-办理回收", "回收贷款-回收维护",
                                   "回收贷款-银行代扣导出", "回收贷款-银行代扣导入", "回收贷款-呆账核销-办理呆账核销",
                                   "回收贷款-呆账核销-呆账核销维护", "回收贷款-核销收回-办理核销收回", "回收贷款-核销收回-核销收回维护",
                                   "回收贷款-抵押质押解除-办理抵押质押解除", "回收贷款-抵押质押解除-抵押质押解除维护","回收贷款-贷款户注销-贷款户注销办理","回收贷款-贷款户注销-贷款户注销维护"};

  public PlOpLoanRecover() {
    this.putValues(keys, values);
  }

}
