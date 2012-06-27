package org.xpup.hafmis.common.util.bizlog;

/**
 * 操作模块-缴存管理
 * @author 王菱
 *2007-6-20 
 */
public class OpModePaymentManage extends BusiLogProMap {

  private static final long serialVersionUID = 4328243096078030108L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENT_DO),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENT_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPAY_DO),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPAY_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_DO),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_EXCESSPAYMENT_DO),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_EXCESSPAYMENT_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENTCONFIRM),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENT_AGENT)
      };

  static final String[] values = { "缴存管理-正常汇缴-办理缴存", "缴存管理-正常汇缴-缴存维护","缴存管理-单位补缴-办理缴存","缴存管理-单位补缴-缴存维护","缴存管理-个人补缴-办理缴存","缴存管理-个人补缴-缴存维护","缴存管理-单位挂账-办理挂账","缴存管理-单位挂账-挂账维护","缴存管理-缴存到账确认","缴存管理-财政代扣"};

  public OpModePaymentManage() {
    this.putValues(keys, values);
  }

}
