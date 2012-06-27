package org.xpup.hafmis.common.util.bizlog;
/**
 * 操作模块-个贷-账务处理
 * @author 王菱
 * 2007-9-13
 */
public class PlOpAccountMange extends BusiLogProMap {

  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.PL_OP_ACCOUNTMANAGE_OPERATIONCHECK),
      new Integer(BusiLogConst.PL_OP_ACCOUNTMANAGE_ACCOUNTING_DO),
      new Integer(BusiLogConst.PL_OP_ACCOUNTMANAGE_DAYCLEANING_INQUIRIES),
      new Integer(BusiLogConst.PL_OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_DO),
      new Integer(BusiLogConst.PL_OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_MODE_ACCOUNTMANAGE_EXCESSPAYMENT_DO),
      new Integer(BusiLogConst.PL_OP_MODE_ACCOUNTMANAGE_EXCESSPAYMENT_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_ACCOUNTMANAGE_LATECARRYOVER),
      new Integer(BusiLogConst.PL_OP_ACCOUNTMANAGE_DAYCLEANING),
      new Integer(BusiLogConst.PL_OP_ACCOUNTMANAGE_MONTHLATECARRYOVER),
      new Integer(BusiLogConst.PL_OP_ACCOUNTMANAGE_CARRYFORWARD)
      };

  static final String[] values = { "账务处理-业务复核", "账务处理-扎账-扎账", "账务处理-扎账-结算单查询",
                                   "账务处理-错账冲正-办理错账调整", "账务处理-错账冲正-错账调整维护", "账务处理-挂账-办理挂账",
                                   "账务处理-挂账-挂账维护", "账务处理-逾期结转", "账务处理-日结",
                                   "账务处理-月末结转","账务处理-年终结转" };

  public PlOpAccountMange() {
    this.putValues(keys, values);
  }

}
