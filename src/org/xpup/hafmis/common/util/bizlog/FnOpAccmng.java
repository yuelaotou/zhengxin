package org.xpup.hafmis.common.util.bizlog;
/**
 * 操作模块-财务-账簿管理
 * @author 王菱
 * 2007-10-8
 */
public class FnOpAccmng extends BusiLogProMap {

  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.FN_OP_ACCMNG_TOTLEACC),
      new Integer(BusiLogConst.FN_OP_ACCMNG_LISTACC),
      new Integer(BusiLogConst.FN_OP_ACCMNG_SEQUENCEACC),
      new Integer(BusiLogConst.FN_OP_ACCMNG_SUBJECTMUSTER),
      new Integer(BusiLogConst.FN_OP_ACCMNG_SUBJECTDAYREPORT),
      new Integer(BusiLogConst.FN_OP_ACCMNG_CASHDAYCLEARREPORT),
      new Integer(BusiLogConst.FN_OP_ACCMNG_BANKDAYCLEARREPORT),
      new Integer(BusiLogConst.FN_OP_ACCMNG_SUBJECTBALANCE)
      };

  static final String[] values = { "账簿管理-总账", "账簿管理-明细账", "账簿管理-序时账",
                                   "账簿管理-凭证汇总表", "账簿管理-科目日报表", "账簿管理-现金日记账" ,
                                   "账簿管理-银行存款日记账", "账簿管理-科目余额表"
                                   };

  public FnOpAccmng() {
    this.putValues(keys, values);
  }

}
