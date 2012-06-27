package org.xpup.hafmis.common.util.bizlog;

/**
 * 操作模块-变更业务
 * @author 王菱
 *2007-6-20 
 */
public class OpModeChange extends BusiLogProMap {

  private static final long serialVersionUID = 4328243096078030108L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGRATE_DO),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGRATE_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_DO),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGPAYMENT_DO),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGPAYMENT_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGPERSON_DO),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGPERSON_MAINTAIN)
      };

  static final String[] values = { "变更业务-汇缴比例调整-办理变更", "变更业务-汇缴比例调整-变更维护","变更业务-工资基数调整-办理变更","变更业务-工资基数调整-变更维护","变更业务-缴额调整-办理变更","变更业务-缴额调整-变更维护","变更业务-人员变更-办理变更","变更业务-人员变更-变更维护" };

  public OpModeChange() {
    this.putValues(keys, values);
  }

}
