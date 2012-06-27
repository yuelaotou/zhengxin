package org.xpup.hafmis.common.util.bizlog;
/**
 * 操作模块-个贷-特殊业务处理
 * @author 王菱
 * 2007-9-13
 */
public class PlOpSpecialBuss extends BusiLogProMap {

  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.PL_OP_SPECIALBUSS_FIVELEVAL_DO),
      new Integer(BusiLogConst.PL_OP_SPECIALBUSS_FIVELEVAL_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_SPECIALBUSS_BONDREGIST_DO),
      new Integer(BusiLogConst.PL_OP_SPECIALBUSS_BONDREGIST_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_SPECIALBUSS_BAILCLEARINTEREST_DO),
      new Integer(BusiLogConst.PL_OP_SPECIALBUSS_BAILCLEARINTEREST_MAINTAIN)
      };

  static final String[] values = { "特殊业务处理-五级分类修改-业务办理", "特殊业务处理-五级分类修改-业务维护", "特殊业务处理-保证金登记-业务办理",
                                   "特殊业务处理-保证金登记-业务维护","特殊业务处理-保证金结息-业务办理","特殊业务处理-保证金结息-业务维护"
                                 };

  public PlOpSpecialBuss() {
    this.putValues(keys, values);
  }

}