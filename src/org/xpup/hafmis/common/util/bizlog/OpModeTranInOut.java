package org.xpup.hafmis.common.util.bizlog;
/**
 * 操作模块-转入转出
 * @author 王菱
 *2007-6-20 
 */
public class OpModeTranInOut extends BusiLogProMap {

  private static final long serialVersionUID = 4328243096078030108L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANOUT_DO),
      new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANOUT_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANIN_PREPARE),
      new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANIN_CHECKIN),
      new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANIN_MAINTAIN)
      };

  static final String[] values = { "转入转出-办理转出-办理转出", "转入转出-办理转出-转出维护","转入转出-办理转入-待转入登记","转入转出-办理转入-转入登记","转入转出-办理转入-转入维护"};

  public OpModeTranInOut() {
    this.putValues(keys, values);
  }

}
