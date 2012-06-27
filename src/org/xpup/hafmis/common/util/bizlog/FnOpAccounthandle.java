package org.xpup.hafmis.common.util.bizlog;
/**
 * 操作模块-财务-账务处理
 * @author 王菱
 * 2007-10-8
 */
public class FnOpAccounthandle extends BusiLogProMap {

  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.FN_OP_ACCOUNTHANDLE_CREDENCEFILLIN),
      new Integer(BusiLogConst.FN_OP_ACCOUNTHANDLE_AUTOTRANSFERS),
      new Integer(BusiLogConst.FN_OP_ACCOUNTHANDLE_CARRYOVERPROFITLOSS),
      new Integer(BusiLogConst.FN_OP_ACCOUNTHANDLE_CREDENCEMAINTAIN),
      new Integer(BusiLogConst.FN_OP_ACCOUNTHANDLE_CREDENCECHECK),
      new Integer(BusiLogConst.FN_OP_ACCOUNTHANDLE_CREDENCECLEAR)
      };

  static final String[] values = { "账务处理-凭证录入-凭证录入", "账务处理-凭证录入-自动转账", "账务处理-凭证录入-损益结转",
                                   "账务处理-凭证录入-凭证维护", "账务处理-凭证审核", "账务处理-凭证过账" };

  public FnOpAccounthandle() {
    this.putValues(keys, values);
  }

}
