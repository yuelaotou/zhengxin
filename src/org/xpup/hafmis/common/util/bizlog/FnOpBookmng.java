package org.xpup.hafmis.common.util.bizlog;
/**
 * 操作模块-财务-账套管理
 * @author 王菱
 * 2007-10-8
 */
public class FnOpBookmng extends BusiLogProMap {

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
      new Integer(BusiLogConst.FN_OP_BOOKMNG_CREATEBOOK)
      };

  static final String[] values = { "账套管理-会计科目", "账套管理-初始数据", "账套管理-启用账套",
                                   "账套管理-凭证字", "账套管理-结算方式", "账套管理-常用摘要",
                                   "账套管理-科目关系设置", "账套管理-凭证模式设置", "账套管理-损益结转设置"
                                   ,"账套管理-创建账套"};

  public FnOpBookmng() {
    this.putValues(keys, values);
  }

}
