package org.xpup.hafmis.common.util.bizlog;

public class OpModeFinance extends BusiLogProMap {

  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.FN_OP_BOOKMNG_SUBJECT),
      new Integer(BusiLogConst.FN_OP_BOOKMNG_DATAINITIALIZE),
      new Integer(BusiLogConst.FN_OP_BOOKMNG_BOOKSTART),
      new Integer(BusiLogConst.FN_OP_BOOKMNG_CREDENCECHAR),
      new Integer(BusiLogConst.FN_OP_BOOKMNG_SETTLEMODLE),
      new Integer(BusiLogConst.FN_OP_BOOKMNG_SUMMARY),
      new Integer(BusiLogConst.FN_OP_BOOKMNG_SUBJECTRELATION),
      new Integer(BusiLogConst.FN_OP_BOOKMNG_CREDENCEMODLE),
      new Integer(BusiLogConst.FN_OP_BOOKMNG_SETTLEINCADDEC),
      new Integer(BusiLogConst.FN_OP_BOOKMNG_CREATEBOOK),

      new Integer(BusiLogConst.FN_OP_ACCOUNTHANDLE_CREDENCEFILLIN),
      new Integer(BusiLogConst.FN_OP_ACCOUNTHANDLE_AUTOTRANSFERS),
      new Integer(BusiLogConst.FN_OP_ACCOUNTHANDLE_CARRYOVERPROFITLOSS),
      new Integer(BusiLogConst.FN_OP_ACCOUNTHANDLE_CREDENCEMAINTAIN),
      new Integer(BusiLogConst.FN_OP_ACCOUNTHANDLE_CREDENCECHECK),
      new Integer(BusiLogConst.FN_OP_ACCOUNTHANDLE_CREDENCECLEAR),
     
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
      new Integer(BusiLogConst.FN_OP_TREASURERMNG_CLEARACCOUNT),

      new Integer(BusiLogConst.FN_OP_ACCMNG_TOTLEACC),
      new Integer(BusiLogConst.FN_OP_ACCMNG_LISTACC),
      new Integer(BusiLogConst.FN_OP_ACCMNG_SEQUENCEACC),
      new Integer(BusiLogConst.FN_OP_ACCMNG_SUBJECTMUSTER),
      new Integer(BusiLogConst.FN_OP_ACCMNG_SUBJECTDAYREPORT),
      new Integer(BusiLogConst.FN_OP_ACCMNG_CASHDAYCLEARREPORT),
      new Integer(BusiLogConst.FN_OP_ACCMNG_BANKDAYCLEARREPORT),
      new Integer(BusiLogConst.FN_OP_ACCMNG_SUBJECTBALANCE),

      new Integer(BusiLogConst.FN_OP_REPORTMNG_FINANCEREPORT_CREATEREPORT)
      
      };

  static final String[] values = { "账套管理-会计科目", "账套管理-初始数据", "账套管理-启用账套",
                                   "账套管理-凭证字", "账套管理-结算方式", "账套管理-常用摘要",
                                   "账套管理-科目关系设置", "账套管理-凭证模式设置", "账套管理-损益结转设置"
                                   ,"账套管理-创建账套",
                                   
                                   "账务处理-凭证录入-凭证录入", "账务处理-凭证录入-自动转账", "账务处理-凭证录入-损益结转",
                                   "账务处理-凭证录入-凭证维护", "账务处理-凭证审核", "账务处理-凭证过账",
                                   
                                   "出纳管理-余额初始", "出纳管理-现金日记账-现金日记账", "出纳管理-现金日记账-自动转账",
                                   "出纳管理-现金日记账-现金日记账维护", "出纳管理-银行存款日记账-银行存款日记账", "出纳管理-银行存款日记账-自动转账" ,
                                   "出纳管理-银行存款日记账-银行存款日记账维护", "出纳管理-银行对账单-银行对账单", "出纳管理-银行对账单-银行对账单维护" ,
                                   "出纳管理-银行存款对账单", "出纳管理-出纳扎账",
                                   
                                   "账簿管理-总账", "账簿管理-明细账", "账簿管理-序时账",
                                   "账簿管理-凭证汇总表", "账簿管理-科目日报表", "账簿管理-现金日记账" ,
                                   "账簿管理-银行存款日记账", "账簿管理-科目余额表",
                                   
                                   "报表管理-财务报表-创建报表"
                                   
                                 };

  public OpModeFinance() {
    this.putValues(keys, values);
  }

}
