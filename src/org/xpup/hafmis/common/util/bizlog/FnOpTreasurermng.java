package org.xpup.hafmis.common.util.bizlog;
/**
 * 操作模块-财务-出纳管理
 * @author 王菱
 * 2007-10-8
 */
public class FnOpTreasurermng extends BusiLogProMap {

  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.FN_OP_TREASURERMNG_BALANCEINITIALIZE),
      new Integer(BusiLogConst.FN_OP_TREASURERMNG_CASHDAYCLEAR_CASHDAYCLEAR),
      new Integer(BusiLogConst.FN_OP_TREASURERMNG_CASHDAYCLEAR_AUTOCASHDAYCLEAR),
      new Integer(BusiLogConst.FN_OP_TREASURERMNG_CASHDAYCLEAR_CASHDAYCLEARMAINTAIN),
      new Integer(BusiLogConst.FN_OP_TREASURERMNG_BANKDAYCLEAR_BANKDAYCLEAR),
      new Integer(BusiLogConst.FN_OP_TREASURERMNG_BANKDAYCLEAR_AUTOBANKDAYCLEAR),
      new Integer(BusiLogConst.FN_OP_TREASURERMNG_BANKDAYCLEAR_BANKDAYCLEARMAINTAIN),
      new Integer(BusiLogConst.FN_OP_TREASURERMNG_BANKCHECKACC_BANKCHECKACC),
      new Integer(BusiLogConst.FN_OP_TREASURERMNG_BANKCHECKACC_BANKCHECKACCMAINTAIN),
      new Integer(BusiLogConst.FN_OP_TREASURERMNG_DEPOSITCHECKACC),
      new Integer(BusiLogConst.FN_OP_TREASURERMNG_CLEARACCOUNT)
      };

  static final String[] values = { "出纳管理-余额初始", "出纳管理-现金日记账-现金日记账", "出纳管理-现金日记账-自动转账",
                                   "出纳管理-现金日记账-现金日记账维护", "出纳管理-银行存款日记账-银行存款日记账", "出纳管理-银行存款日记账-自动转账" ,
                                   "出纳管理-银行存款日记账-银行存款日记账维护", "出纳管理-银行对账单-银行对账单", "出纳管理-银行对账单-银行对账单维护" ,
                                   "出纳管理-银行存款对账单", "出纳管理-出纳扎账"
                                   };

  public FnOpTreasurermng() {
    this.putValues(keys, values);
  }

}
