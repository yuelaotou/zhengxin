package org.xpup.hafmis.common.util.bizlog;

public class OpModeColl extends BusiLogProMap {

  private static final long serialVersionUID = 4328243096078030108L;

  static final Integer[] keys = {
    
      new Integer(BusiLogConst.OP_MODE_OPEN_ORGOPEN_DO),
      new Integer(BusiLogConst.OP_MODE_OPEN_ORGOPEN_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_OPEN_EMPOPEN),
      new Integer(BusiLogConst.OP_MODE_OPEN_ORGCHG_DO),
      new Integer(BusiLogConst.OP_MODE_OPEN_ORGCHG_MAINTAIN),
    
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGRATE_DO),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGRATE_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_DO),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGPAYMENT_DO),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGPAYMENT_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGPERSON_DO),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGPERSON_MAINTAIN),
    
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENT_DO),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENT_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPAY_DO),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPAY_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_DO),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_EXCESSPAYMENT_DO),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_EXCESSPAYMENT_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENTCONFIRM),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENT_AGENT),
    
      new Integer(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO),
      new Integer(BusiLogConst.OP_MODE_DRAWING_DRAWING_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_DRAWING_SPECIALDRAWING_DO),
      new Integer(BusiLogConst.OP_MODE_DRAWING_SPECIALDRAWING_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_DRAWING_COLLLOANBACK),
    
      new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANOUT_DO),
      new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANOUT_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANIN_PREPARE),
      new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANIN_CHECKIN),
      new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANIN_MAINTAIN),
    
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

  static final String[] values = { "单位开户-办理开户", "单位开户-开户维护","职工开户",
                                   "单位变更-办理变更","单位变更-变更维护",
                                   
                                   "变更业务-汇缴比例调整-办理变更", "变更业务-汇缴比例调整-变更维护","变更业务-工资基数调整-办理变更",
                                   "变更业务-工资基数调整-变更维护","变更业务-缴额调整-办理变更","变更业务-缴额调整-变更维护",
                                   "变更业务-人员变更-办理变更","变更业务-人员变更-变更维护" ,
                                   
                                   "缴存管理-正常汇缴-办理缴存","缴存管理-正常汇缴-缴存维护", "缴存管理-单位补缴-办理缴存","缴存管理-单位补缴-缴存维护",
                                   "缴存管理-个人补缴-办理缴存","缴存管理-个人补缴-缴存维护","缴存管理-单位挂账-办理挂账",
                                   "缴存管理-单位挂账-挂账维护","缴存管理-缴存到账确认" ,"缴存管理-财政代扣",
                                   
                                   "提取管理-办理提取-办理提取", "提取管理-办理提取-提取维护","提取管理-办理特殊提取-办理提取",
                                   "提取管理-办理特殊提取-提取维护","公积金还贷",
                                   
                                   "转入转出-办理转出-办理转出", "转入转出-办理转出-转出维护","转入转出-办理转入-待转入登记",
                                   "转入转出-办理转入-转入登记","转入转出-办理转入-转入维护",
                                   
                                   "账务处理-业务复核", "账务处理-错账调整-办理错账调整","账务处理-错账调整-错账调整维护",
                                   "账务处理-扎账-扎账","账务处理-扎账-结算单查询","账务处理-日结-扎账",
                                   "账务处理-日结-结算单查询","账务处理-月结-扎账" ,"账务处理-月结-结算单查询",
                                   "账务处理-年终结息", "账务处理-维护利率","人民银行数据导出-导出报文参数设置",
                                   "人民银行数据导出-导出报文头设置","人民银行数据导出-导出报文"
                                   
                                 };

  public OpModeColl() {
    this.putValues(keys, values);
  }

}
