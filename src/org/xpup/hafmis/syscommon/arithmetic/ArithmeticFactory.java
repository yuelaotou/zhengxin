package org.xpup.hafmis.syscommon.arithmetic;

import org.xpup.hafmis.common.util.BusiConst;

public class ArithmeticFactory{
  private static ArithmeticFactory arithmetic;
  ArithmeticFactory(){
      
    }
  public static synchronized ArithmeticFactory getArithmetic(){
    if (arithmetic == null) {
      arithmetic = new ArithmeticFactory();
    }
    return arithmetic;
  }
  /**
   * 返回特定的算法DAO类
   * @param paymentAccuracy
   * @return
   */
  public ArithmeticInterface getArithmeticDAO(int paymentAccuracy){
    ArithmeticInterface arithmeticInterface = null;
    switch (paymentAccuracy){
      case BusiConst.PAYMENTACCURACY_ROUNDTOYUAN://四舍五入到元
        arithmeticInterface = new RoundtoYuanDAO();
        break;
      case BusiConst.PAYMENTACCURACY_DISCARDTOYUAN://舍元以下
        arithmeticInterface = new DiscardtoYuanDAO();
        break;
      case BusiConst.PAYMENTACCURACY_SEEKOKONYUAN://见角进元
        arithmeticInterface = new SeekokonYuanDAO();
        break;
      case BusiConst.PAYMENTACCURACY_SEECENTSONKOK://见分进角
        arithmeticInterface = new SeeCenttoChiaoDAO();
        break;
      case BusiConst.PAYMENTACCURACY_ROUNDTOKOK://四舍五入到角
        arithmeticInterface = new RounttoChiaoDAO();
        break;
      case BusiConst.PAYMENTACCURACY_DISCARDTOKOK://舍角以下
        arithmeticInterface = new DiscardChiaoDAO();
        break;
      case BusiConst.PAYMENTACCURACY_SEEKOKCENTSONYUAN://见角分进元
        arithmeticInterface = new SeekChiaoCenttoYuanDAO();
        break;
      case BusiConst.PAYMENTACCURACY_ROUNDTOCENT://四舍五入到分
        arithmeticInterface = new RounttoCentDAO();
    } 
    return arithmeticInterface;
  }
}