package org.xpup.hafmis.common.util.enumerate;
/**
 * 居住情况
 * @author 王菱
 *2007-6-22
 */
import org.xpup.hafmis.common.util.BusiConst;

public class LivingContext extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.LIVINGCONTEXT_REHOUSINGYOURSELF),
      new Integer(BusiConst.LIVINGCONTEXT_MORTGAGE),
      new Integer(BusiConst.LIVINGCONTEXT_RELATIVEHOME),
      new Integer(BusiConst.LIVINGCONTEXT_COLLECTIVEQUARTERS),
      new Integer(BusiConst.LIVINGCONTEXT_RENTHOME),
      new Integer(BusiConst.LIVINGCONTEXT_PUBLICHOME),
      new Integer(BusiConst.LIVINGCONTEXT_OTHERS),
      new Integer(BusiConst.LIVINGCONTEXT_UNKNOW)
      };

   static final String[] values = { "自置", "按揭", "亲属楼宇", "集体宿舍", "租房", "共有住宅", "其他", "未知" };
  public LivingContext()
  {
    this.putValues(keys,values);
  }
}
