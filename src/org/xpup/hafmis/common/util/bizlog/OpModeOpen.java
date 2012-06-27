package org.xpup.hafmis.common.util.bizlog;

/**
 * 操作模块-开户销户
 * @author 王菱
 *2007-6-20 
 */
public class OpModeOpen extends BusiLogProMap {

  private static final long serialVersionUID = 4328243096078030108L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.OP_MODE_OPEN_ORGOPEN_DO),
      new Integer(BusiLogConst.OP_MODE_OPEN_ORGOPEN_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_OPEN_EMPOPEN),
      new Integer(BusiLogConst.OP_MODE_OPEN_ORGCHG_DO),
      new Integer(BusiLogConst.OP_MODE_OPEN_ORGCHG_MAINTAIN) };

  static final String[] values = { "单位开户-办理开户", "单位开户-开户维护", "职工开户","单位变更-办理变更","单位变更-变更维护" };

  public OpModeOpen() {
    this.putValues(keys, values);
  }

}
