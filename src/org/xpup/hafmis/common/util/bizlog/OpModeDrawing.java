package org.xpup.hafmis.common.util.bizlog;
/**
 * 操作模块-提取管理
 * @author 王菱
 *2007-6-20 
 */
public class OpModeDrawing extends BusiLogProMap {

  private static final long serialVersionUID = 4328243096078030108L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO),
      new Integer(BusiLogConst.OP_MODE_DRAWING_DRAWING_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_DRAWING_SPECIALDRAWING_DO),
      new Integer(BusiLogConst.OP_MODE_DRAWING_SPECIALDRAWING_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_DRAWING_COLLLOANBACK)
      };

  static final String[] values = { "提取管理-办理提取-办理提取", "提取管理-办理提取-提取维护","提取管理-办理特殊提取-办理提取","提取管理-办理特殊提取-提取维护","公积金还贷"};

  public OpModeDrawing() {
    this.putValues(keys, values);
  }

}
