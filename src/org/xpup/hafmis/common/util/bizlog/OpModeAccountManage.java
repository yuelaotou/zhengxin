package org.xpup.hafmis.common.util.bizlog;
/**
 * 操作模块-账务处理
 * @author 王菱
 *2007-6-20 
 */
public class OpModeAccountManage extends BusiLogProMap {

  private static final long serialVersionUID = 4328243096078030108L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.OP_MODE_ACCOUNTMANAGE_OPERATIONCHECK),
      new Integer(BusiLogConst.OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_DO),
      new Integer(BusiLogConst.OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_ACCOUNTMANAGE_ACCOUNTING_DO),
      new Integer(BusiLogConst.OP_MODE_ACCOUNTMANAGE_ACCOUNTING_INQUIRIES),
      new Integer(BusiLogConst.OP_MODE_ACCOUNTMANAGE_DAYCLEANING_DO),
      new Integer(BusiLogConst.OP_MODE_ACCOUNTMANAGE_DAYCLEANING_INQUIRIES),
      new Integer(BusiLogConst.OP_MODE_ACCOUNTMANAGE_MONTHCLEANING_DO),
      new Integer(BusiLogConst.OP_MODE_ACCOUNTMANAGE_MONTHCLEANING_INQUIRIES),
      new Integer(BusiLogConst.OP_MODE_ACCOUNTMANAGE_INTEREST),
      new Integer(BusiLogConst.OP_MODE_ACCOUNTMANAGE_MAINTAINRATE),
      new Integer(BusiLogConst.OP_MODE_BANKDATAEXP_RECORDPARAMSETTING),
      new Integer(BusiLogConst.OP_MODE_BANKDATAEXP_RECORDHEADSETTING),
      new Integer(BusiLogConst.OP_MODE_BANKDATAEXP_RECORD)
      };

  static final String[] values = { "账务处理-业务复核", "账务处理-错账调整-办理错账调整","账务处理-错账调整-错账调整维护","账务处理-扎账-扎账","账务处理-扎账-结算单查询","账务处理-日结-扎账","账务处理-日结-结算单查询","账务处理-月结-扎账","账务处理-月结-结算单查询","账务处理-年终结息","账务处理-维护利率","人民银行数据导出-导出报文参数设置","人民银行数据导出-导出报文头设置","人民银行数据导出-导出报文"};

  public OpModeAccountManage() {
    this.putValues(keys, values);
  }

}
